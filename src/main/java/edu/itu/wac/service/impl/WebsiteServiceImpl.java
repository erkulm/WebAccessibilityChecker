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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WebsiteServiceImpl implements WebsiteService {
    private final WebsiteCategoryRepository websiteCategoryRepository;
    private final WebsiteRepository websiteRepository;
    private final MapperFacade mapperFacade;

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

    @Override
    public WebsiteResponse createNewWebsiteFromAddress(String address){
        Optional<Website> oldWebsite = websiteRepository.findByAddress(address);
        if (oldWebsite.isEmpty()){
            Website website = new Website();
            Optional<WebsiteCategory> general = websiteCategoryRepository.findByName("GENERAL");
            if (general.isEmpty()){
                WebsiteCategory category = new WebsiteCategory();
                category.setName("GENERAL");
                category = websiteCategoryRepository.save(category);
                website.setCategory(category);
            }
            general.ifPresent(website::setCategory);
            website.setAddress(address);
            website = websiteRepository.save(website);
            return mapperFacade.map(website,WebsiteResponse.class);
        }else{
            return mapperFacade.map(oldWebsite.get(),WebsiteResponse.class);
        }
    }

    @Override
    public WebsiteResponse updateLatestTestDate(String address) {
        Optional<Website> websiteOptional = websiteRepository.findByAddress(address);
        if (websiteOptional.isPresent()){
            Website website = websiteOptional.get();
            website.setLatestTestDate(LocalDateTime.now());
            website = websiteRepository.save(website);
            return mapperFacade.map(website,WebsiteResponse.class);
        }
        return null;
    }
}
