package edu.itu.wac.controller;

import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.WebsiteCategoryService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.WebsiteResponse;
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
    List<ErrorResponse> getWebsiteByAddress(@RequestParam @NotNull String address){
        return errorService.findByWebsiteAddress(address);
    }
}
