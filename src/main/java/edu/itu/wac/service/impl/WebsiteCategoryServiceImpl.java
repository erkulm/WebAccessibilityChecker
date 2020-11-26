package edu.itu.wac.service.impl;

import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.repository.WebsiteCategoryRepository;
import edu.itu.wac.service.WebsiteCategoryService;
import edu.itu.wac.service.request.WebsiteCategoryRequest;
import edu.itu.wac.service.response.WebsiteCategoryResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebsiteCategoryServiceImpl implements WebsiteCategoryService {
    private static WebsiteCategoryRepository websiteCategoryRepository;
    private static MapperFacade mapperFacade;

    @Autowired
    public WebsiteCategoryServiceImpl(WebsiteCategoryRepository websiteCategoryRepository,
                                      @Qualifier(value = "websiteCategoryServiceMapper") MapperFacade mapperFacade) {
        this.websiteCategoryRepository = websiteCategoryRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public WebsiteCategoryResponse save(WebsiteCategoryRequest websiteCategoryRequest) {
        WebsiteCategory websiteCategory = mapperFacade.map(websiteCategoryRequest, WebsiteCategory.class);
        websiteCategory = websiteCategoryRepository.save(websiteCategory);
        return mapperFacade.map(websiteCategory, WebsiteCategoryResponse.class);
    }

    @Override
    public WebsiteCategory findByName(String name) {
        Optional<WebsiteCategory> websiteCategory = websiteCategoryRepository.findByName(name);
        return websiteCategory.orElse(null);
    }
}
