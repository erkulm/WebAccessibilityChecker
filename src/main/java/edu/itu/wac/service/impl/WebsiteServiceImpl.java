package edu.itu.wac.service.impl;

import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.repository.WebsiteCategoryRepository;
import edu.itu.wac.repository.WebsiteRepository;
import edu.itu.wac.service.WebsiteCategoryService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.request.WebsiteCategoryRequest;
import edu.itu.wac.service.request.WebsiteRequest;
import edu.itu.wac.service.response.WebsiteCategoryResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebsiteServiceImpl implements WebsiteService {
    private static WebsiteCategoryRepository websiteCategoryRepository;
    private static WebsiteRepository websiteRepository;
    private static MapperFacade mapperFacade;

    @Autowired
    public WebsiteServiceImpl(WebsiteCategoryRepository websiteCategoryRepository,
                              WebsiteRepository websiteRepository,
                              @Qualifier(value = "websiteServiceMapper") MapperFacade mapperFacade) {
        this.websiteCategoryRepository = websiteCategoryRepository;
        this.websiteRepository = websiteRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public WebsiteResponse save(WebsiteRequest websiteRequest) {
        Website website = mapperFacade.map(websiteRequest, Website.class);
        website = websiteRepository.save(website);
        return mapperFacade.map(website, WebsiteResponse.class);
    }

    @Override
    public WebsiteResponse findByAddress(String address) {
        Optional<Website> website = websiteRepository.findByAddress(address);
        return mapperFacade.map(website.orElse(null), WebsiteResponse.class);
    }
}
