package edu.itu.wac.service.impl;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.repository.ErrorReportRepository;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.WebsiteCategoryService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import edu.itu.wac.util.Pa11yUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ErrorServiceImpl implements ErrorService {
    private final WebsiteService websiteService;
    private final WebsiteCategoryService websiteCategoryService;
    private final ErrorReportRepository errorReportRepository;
    private final MapperFacade mapperFacade;

    @Value("${test.min.day.difference}")
    Integer testDayDifference;

    @Autowired
    public ErrorServiceImpl(WebsiteService websiteService,
                            WebsiteCategoryService websiteCategoryService,
                            ErrorReportRepository errorReportRepository,
                            @Qualifier(value = "errorServiceMapper") MapperFacade mapperFacade) {
        this.websiteService = websiteService;
        this.websiteCategoryService = websiteCategoryService;
        this.errorReportRepository = errorReportRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public ErrorResponse save(ErrorRequest errorRequest) {
        Error error = mapperFacade.map(errorRequest, Error.class);
        error = errorReportRepository.save(error);
        return mapperFacade.map(error, ErrorResponse.class);
    }

    @Override
    public List<ErrorResponse> findByWebsiteAddress(String address) {
        WebsiteResponse website = websiteService.findByAddress(address);
        if (website != null) {
            List<Error> errors = errorReportRepository.findAllByWebsite_IdAndTestCrDateIsAfter(
                    website.getId(), LocalDateTime.now().minusDays(testDayDifference));
            return mapperFacade.mapAsList(errors, ErrorResponse.class);
        } else {
            return null;
        }
    }

    @Override
    public List<ErrorResponse> saveAll(List<ErrorRequest> errorRequests) {
        List<Error> errors = mapperFacade.mapAsList(errorRequests, Error.class);
        errors = errorReportRepository.saveAll(errors);
        return mapperFacade.mapAsList(errors, ErrorResponse.class);
    }

    @Override
    public List<ErrorResponse> generateReport(String address){
        WebsiteResponse websiteResponse = websiteService.findByAddress(address);
        if (websiteResponse == null) {
            websiteResponse = websiteService.createNewWebsiteFromAddress(address);
        }
        List<ErrorResponse> errorResponses;
        if (websiteResponse.getLatestTestDate() == null
                || websiteResponse.getLatestTestDate().plusDays(testDayDifference).isBefore(LocalDateTime.now())) {
            List<Error> errors = Pa11yUtil.runPa11y(
                    mapperFacade.map(websiteResponse, Website.class),
                    "", mapperFacade.map(websiteResponse.getCategory(), WebsiteCategory.class));

            errorResponses = saveAll(mapperFacade.mapAsList(errors, ErrorRequest.class));
            websiteService.updateLatestTestDate(address);
        } else {
            errorResponses = findByWebsiteAddress(address);
        }
        return errorResponses;
    }
}
