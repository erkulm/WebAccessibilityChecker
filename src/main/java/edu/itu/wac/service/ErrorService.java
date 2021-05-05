package edu.itu.wac.service;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.Website;
import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.response.ErrorResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ErrorService {
    ErrorResponse save(ErrorRequest errorRequest);

    List<ErrorResponse> getAll();

    List<ErrorResponse> findByWebsiteAddress(String address);
    List<ErrorResponse> saveAll(List<ErrorRequest> errorRequests);

    List<ErrorResponse> generateReport(String address, Boolean isHtmlHistoryEnabled);

    List<ErrorResponse> generateDeepReport(String address, Boolean isHtmlHistoryEnabled);

    List<ErrorResponse> saveErrorReport(Website website, long startTime, ErrorReport errorReport);

    String findErrorReportByErrorId(String id);

    Workbook generateExcel(List<ErrorResponse> items);
}
