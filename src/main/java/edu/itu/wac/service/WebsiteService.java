package edu.itu.wac.service;

import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.service.request.WebsiteCategoryRequest;
import edu.itu.wac.service.request.WebsiteRequest;
import edu.itu.wac.service.response.WebsiteCategoryResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import org.springframework.stereotype.Service;

@Service
public interface WebsiteService {
    WebsiteResponse save(WebsiteRequest websiteRequest);
    WebsiteResponse findByAddress(String address);
}
