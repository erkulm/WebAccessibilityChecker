package edu.itu.wac.service;

import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.request.WebsiteRequest;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ErrorService {
    ErrorResponse save(ErrorRequest errorRequest);
    List<ErrorResponse> findByWebsiteAddress(String address);
    List<ErrorResponse> saveAll(List<ErrorRequest> errorRequests);

    List<ErrorResponse> generateReport(String address);
}
