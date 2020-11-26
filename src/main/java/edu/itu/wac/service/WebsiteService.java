package edu.itu.wac.service;

import edu.itu.wac.service.request.WebsiteRequest;
import edu.itu.wac.service.response.WebsiteResponse;
import org.springframework.stereotype.Service;

@Service
public interface WebsiteService {
    WebsiteResponse save(WebsiteRequest websiteRequest);
    WebsiteResponse findByAddress(String address);
}
