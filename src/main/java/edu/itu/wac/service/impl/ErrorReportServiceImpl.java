package edu.itu.wac.service.impl;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.Website;
import edu.itu.wac.repository.ErrorReportRepository;
import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.request.ComparisonRequest;
import edu.itu.wac.service.response.*;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ErrorReportServiceImpl implements ErrorReportService {
    private final WebsiteService websiteService;
    private final ErrorReportRepository errorReportRepository;
    private final MapperFacade mapperFacade;

    @Value("${test.min.day.difference}")
    Integer testDayDifference;

    @Autowired
    public ErrorReportServiceImpl(WebsiteService websiteService,
                                  ErrorReportRepository errorReportRepository,
                                  @Qualifier(value = "errorReportServiceMapper") MapperFacade mapperFacade) {
        this.websiteService = websiteService;
        this.errorReportRepository = errorReportRepository;
        this.mapperFacade = mapperFacade;
    }


    @Cacheable("errorReports")
    @Override
    public List<ErrorReportResponse> getAll() {
        return mapperFacade.mapAsList(errorReportRepository.findAll(), ErrorReportResponse.class);
    }

    @Override
    public List<ErrorReportResponse> findByWebsiteAddress(String address) {
      List<ErrorReportResponse> result = new ArrayList<>();
      List<ErrorReport> reportsByWebsiteContaining = findByWebsiteAddressContaining(address);
      if (reportsByWebsiteContaining != null) {
        result =
                mapperFacade.mapAsList(
                        reportsByWebsiteContaining, ErrorReportResponse.class);
      }
      return result;
    }

  @Override
  public List<ErrorReportWithoutSubpagesResponse> findByWebsiteAddressWithoutSubpages(String address) {
    List<ErrorReportWithoutSubpagesResponse> result = new ArrayList<>();
    List<ErrorReport> reportsByWebsiteContaining = findByWebsiteAddressContaining(address);
    if (reportsByWebsiteContaining != null) {
      result =
          mapperFacade.mapAsList(
              reportsByWebsiteContaining, ErrorReportWithoutSubpagesResponse.class);
      }
    return result;
  }

  public List<ErrorReport> findByWebsiteAddressContaining(String address) {
    List<WebsiteResponse> website;
    if (address == null) {
      website = websiteService.getAll();
    } else {
      website = websiteService.findByAddressContaining(address);
    }
    if (!website.isEmpty()) {
      return errorReportRepository.findAllByWebsiteIn(
          mapperFacade.mapAsList(website, Website.class));
    } else {
      return Collections.emptyList();
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
        return Collections.emptyList();
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