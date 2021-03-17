package edu.itu.wac.service.impl;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.job.Pa11yExecutor;
import edu.itu.wac.repository.ErrorReportRepository;
import edu.itu.wac.repository.ErrorRepository;
import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.request.ComparisonRequest;
import edu.itu.wac.service.response.ComparisonResult;
import edu.itu.wac.service.response.ErrorCountInfo;
import edu.itu.wac.service.response.ErrorReportResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ErrorReportServiceImpl implements ErrorReportService {
    private final WebsiteService websiteService;
    private final ErrorRepository errorRepository;
    private final ErrorReportRepository errorReportRepository;
    private final Pa11yExecutor pa11yExecutor;
    private final MapperFacade mapperFacade;

    @Value("${test.min.day.difference}")
    Integer testDayDifference;

    @Autowired
    public ErrorReportServiceImpl(WebsiteService websiteService,
                                  ErrorRepository errorRepository,
                                  ErrorReportRepository errorReportRepository,
                                  Pa11yExecutor pa11yExecutor,
                                  @Qualifier(value = "errorReportServiceMapper") MapperFacade mapperFacade) {
        this.websiteService = websiteService;
        this.errorRepository = errorRepository;
        this.errorReportRepository = errorReportRepository;
        this.pa11yExecutor = pa11yExecutor;
        this.mapperFacade = mapperFacade;
    }


    @Cacheable("errorReports")
    @Override
    public List<ErrorReportResponse> getAll() {
        return mapperFacade.mapAsList(errorReportRepository.findAll(), ErrorReportResponse.class);
    }

    @Override
    public List<ErrorReportResponse> findByWebsiteAddress(String address) {
        WebsiteResponse website = websiteService.findByAddress(address);
        if (website != null) {
//            return getAll().stream()
//                    .filter(er->website.getAddress().equalsIgnoreCase(address))
//                    .collect(Collectors.toList());
            List<ErrorReport> errorReports = errorReportRepository.findAllByWebsite_Id(
                    website.getId());
            return mapperFacade.mapAsList(errorReports, ErrorReportResponse.class);
        } else {
            return null;
        }
    }

    @Override
    public ErrorReportResponse findById(String id) {
        return mapperFacade.map(errorReportRepository.findById(id).orElse(null), ErrorReportResponse.class);
    }

    @Override
    public List<ErrorCountInfo> getErrorCountInfo(String id) {
        List<ErrorCountInfo> errorCountInfoList = new ArrayList<>();
        Optional<ErrorReport> errorReportOptional = errorReportRepository.findById(id);
        if (errorReportOptional.isPresent()) {
            ErrorReport errorReport = errorReportOptional.get();
            List<Error> errors = errorReport.getSubPageErrors()
                    .stream()
                    .flatMap(spe -> spe.getErrors().stream())
                    .collect(Collectors.toList());
            Map<String, List<Error>> groupedErrors
                    = errors.stream().collect(Collectors.groupingBy(Error::getErrorDesc));
            for (int i = 0; i < 10; i++) {
                ErrorCountInfo errorCountInfo = getErrorCountInfo(groupedErrors);
                if (errorCountInfo != null) {
                    errorCountInfoList.add(errorCountInfo);
                }
            }
            errorCountInfoList.sort(Comparator.comparing(ErrorCountInfo::getErrorCount).reversed());
            return errorCountInfoList;
        }
        return null;
    }

    @Override
    public List<ComparisonResult> getComparisonData(ComparisonRequest comparisonRequest) {
        Iterable<ErrorReport> errorReportsIterable = errorReportRepository.findAllById(comparisonRequest.getReportIds());
        List<ErrorReport> errorReports = StreamSupport.stream(errorReportsIterable.spliterator(), false)
                .collect(Collectors.toList());
        List<ComparisonResult> comparisonResultList = new ArrayList<>();
        for (ErrorReport errorReport : errorReports) {
            List<Error> errors = errorReport.getSubPageErrors()
                    .stream()
                    .flatMap(spe -> spe.getErrors().stream())
                    .collect(Collectors.toList());
            Map<String, Long> comparisonData = errors.stream()
                    .collect(Collectors.groupingBy(Error::getDocument, Collectors.counting()));
            ComparisonResult result = new ComparisonResult();
            result.setData(comparisonData);
            result.setAddress(errorReport.getWebsite().getAddress());
            comparisonResultList.add(result);
        }
        if (!comparisonResultList.isEmpty()) {
            comparisonResultList.forEach(c -> addZeroErrors(c, comparisonResultList));
        }
        return comparisonResultList;
    }

    @Override
    @CachePut(value="errorReports")
    public ErrorReport save(ErrorReport errorReport) {
        return errorReportRepository.save(errorReport);
    }

    private void addZeroErrors(ComparisonResult c, List<ComparisonResult> comparisonResultList) {
        c.getData().forEach((key, value) ->
                comparisonResultList.forEach(cr -> addKeyValue(cr, key)));
    }

    private void addKeyValue(ComparisonResult cr, String key) {
        if (!cr.getData().containsKey(key)) {
            cr.getData().put(key, 0L);
        }
    }

    private ErrorCountInfo getErrorCountInfo(Map<String, List<Error>> groupedErrors) {
        if (!groupedErrors.isEmpty()) {
            ErrorCountInfo errorCountInfo = new ErrorCountInfo();
            int temp = 0;
            Map.Entry<String, List<Error>> pairWithMostOccurrences = null;
            Iterator<Map.Entry<String, List<Error>>> iterator = groupedErrors.entrySet().iterator();
            for (Map.Entry<String, List<Error>> pair : groupedErrors.entrySet()) {
                if (pair.getValue().size() > temp) {
                    temp = pair.getValue().size();
                    pairWithMostOccurrences = pair;
                }
            }
            if (pairWithMostOccurrences != null) {
                errorCountInfo.setDocument(pairWithMostOccurrences.getValue().get(0).getDocument());
                errorCountInfo.setErrorCount(temp);
                errorCountInfo.setErrorDesc(pairWithMostOccurrences.getKey());
                groupedErrors.remove(pairWithMostOccurrences.getKey());
                return errorCountInfo;
            }
        }
        return null;
    }
}