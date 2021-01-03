package edu.itu.wac.service.impl;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.job.Pa11yExecutor;
import edu.itu.wac.repository.ErrorReportRepository;
import edu.itu.wac.repository.ErrorRepository;
import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.response.ErrorReportResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public List<ErrorReportResponse> getAll() {
        return mapperFacade.mapAsList(errorReportRepository.findAll(), ErrorReportResponse.class);
    }

    @Override
    public List<ErrorReportResponse> findByWebsiteAddress(String address) {
        WebsiteResponse website = websiteService.findByAddress(address);
        if (website != null) {
            List<ErrorReport> errorReports = errorReportRepository.findAllByWebsite_Id(
                    website.getId());
            return mapperFacade.mapAsList(errorReports, ErrorReportResponse.class);
        } else {
            return null;
        }
    }
}