package edu.itu.wac.service.impl;

import edu.itu.wac.entity.Error;
import edu.itu.wac.repository.ErrorReportRepository;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.WebsiteCategoryService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.WebsiteResponse;
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
}
