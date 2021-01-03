package edu.itu.wac.service;

import edu.itu.wac.service.response.ErrorReportResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ErrorReportService {

    List<ErrorReportResponse> getAll();

    List<ErrorReportResponse> findByWebsiteAddress(String address);
}
