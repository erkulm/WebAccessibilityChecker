package edu.itu.wac.controller;

import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.repository.WebsiteCategoryRepository;
import edu.itu.wac.service.WebsiteCategoryService;
import edu.itu.wac.service.response.WebsiteCategoryResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class WebsiteCategoryRestController {
    private static WebsiteCategoryService websiteCategoryService;
    private static MapperFacade mapperFacade;

    @Autowired
    public WebsiteCategoryRestController(WebsiteCategoryService websiteCategoryService,
                                      @Qualifier(value = "websiteCategoryServiceMapper") MapperFacade mapperFacade) {
        this.websiteCategoryService = websiteCategoryService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping("/website-category")
    @ResponseBody
    WebsiteCategoryResponse getWebsiteCategoryByName(@RequestParam @NotNull String name){
        WebsiteCategory websiteCategory = websiteCategoryService.findByName(name);
        return mapperFacade.map(websiteCategory, WebsiteCategoryResponse.class);
    }
}
