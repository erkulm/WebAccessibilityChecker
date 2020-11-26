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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class ErrorRestController {
    private static WebsiteCategoryService websiteCategoryService;
    private static WebsiteService websiteService;
    private static ErrorService errorService;
    private static MapperFacade mapperFacade;

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
        if (websiteResponse == null){
            WebsiteRequest websiteRequest = new WebsiteRequest();
            websiteRequest.setAddress(address);
            WebsiteCategoryRequest category = new WebsiteCategoryRequest();
            category.setName("general");
            WebsiteCategoryResponse savedCategory = websiteCategoryService.save(category);
            websiteRequest.setCategory(mapperFacade.map(savedCategory, WebsiteCategory.class));
            websiteResponse = websiteService.save(websiteRequest);

        }
        List<Error> errors = Pa11yUtil.runPa11y(
                               mapperFacade.map(websiteResponse, Website.class),
                          "", websiteResponse.getCategory());
        errors.forEach(error -> errorService.save(mapperFacade.map(error, ErrorRequest.class)));
        return errorService.findByWebsiteAddress(address);
    }
}
