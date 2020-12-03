package edu.itu.wac.controller;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.WebsiteCategoryService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.request.WebsiteCategoryRequest;
import edu.itu.wac.service.request.WebsiteRequest;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.WebsiteCategoryResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import edu.itu.wac.util.Pa11yUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ErrorRestController {
    private final WebsiteCategoryService websiteCategoryService;
    private final WebsiteService websiteService;
    private final ErrorService errorService;
    private final MapperFacade mapperFacade;

    @Value("${test.min.day.difference}")
    private Integer minDayDifference;

    @Autowired
    public ErrorRestController(WebsiteCategoryService websiteCategoryService,
                               WebsiteService websiteService,
                               ErrorService errorService,
                               @Qualifier(value = "websiteServiceMapper") MapperFacade mapperFacade) {
        this.websiteCategoryService = websiteCategoryService;
        this.websiteService = websiteService;
        this.errorService = errorService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping("/error-report")
    @ResponseBody
    List<ErrorResponse> getWebsiteByAddress(@RequestParam @NotNull String address) {
        return errorService.findByWebsiteAddress(address);
    }

    @GetMapping("/generate-report")
    @ResponseBody
    List<ErrorResponse> generateNewReport(@RequestParam @NotNull String address) {
        WebsiteResponse websiteResponse = websiteService.findByAddress(address);
        if (websiteResponse == null) {
            websiteResponse = websiteService.createNewWebsiteFromAddress(address);
        }
        List<ErrorResponse> errorResponses;
        if (websiteResponse.getLatestTestDate() == null
                || websiteResponse.getLatestTestDate().plusDays(minDayDifference).isBefore(LocalDateTime.now())) {
            List<Error> errors = Pa11yUtil.runPa11y(
                    mapperFacade.map(websiteResponse, Website.class),
                    "", mapperFacade.map(websiteResponse.getCategory(), WebsiteCategory.class));

            errorResponses = errorService.saveAll(mapperFacade.mapAsList(errors, ErrorRequest.class));
            websiteService.updateLatestTestDate(address);
        } else {
            errorResponses = errorService.findByWebsiteAddress(address);
        }
        return errorResponses;
    }
}
