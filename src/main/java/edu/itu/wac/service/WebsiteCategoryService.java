package edu.itu.wac.service;

import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.service.request.WebsiteCategoryRequest;
import edu.itu.wac.service.response.WebsiteCategoryResponse;
import org.springframework.stereotype.Service;

@Service
public interface WebsiteCategoryService {
    WebsiteCategoryResponse save(WebsiteCategoryRequest websiteCategoryRequest);
    WebsiteCategory findByName(String name);
}
