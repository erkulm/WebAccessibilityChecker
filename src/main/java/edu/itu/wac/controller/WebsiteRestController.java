package edu.itu.wac.controller;

import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.service.WebsiteCategoryService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.response.WebsiteCategoryResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class WebsiteRestController {
    private static WebsiteCategoryService websiteCategoryService;
    private static WebsiteService websiteService;
    private static MapperFacade mapperFacade;

    @Autowired
    public WebsiteRestController(WebsiteCategoryService websiteCategoryService,
                                 WebsiteService websiteService,
                                 @Qualifier(value = "websiteServiceMapper") MapperFacade mapperFacade) {
        this.websiteCategoryService = websiteCategoryService;
        this.websiteService = websiteService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping("/website")
    @ResponseBody
    WebsiteResponse getWebsiteByAddress(@RequestParam @NotNull String address){
        return websiteService.findByAddress(address);
    }
}
