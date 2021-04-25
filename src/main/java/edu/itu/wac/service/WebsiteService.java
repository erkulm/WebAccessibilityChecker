package edu.itu.wac.service;

import edu.itu.wac.service.request.WebsiteRequest;
import edu.itu.wac.service.response.WebsiteResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WebsiteService {
    WebsiteResponse save(WebsiteRequest websiteRequest);
    WebsiteResponse findByAddress(String address);

    List<WebsiteResponse> findByAddressContaining(String address);

    WebsiteResponse createNewWebsiteFromAddress(String address);
    WebsiteResponse updateLatestTestDate(String address);
    List<WebsiteResponse> getAll();
}
