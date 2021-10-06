package edu.itu.wac.service;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.service.request.ComparisonRequest;
import edu.itu.wac.service.response.ComparisonResult;
import edu.itu.wac.service.response.ErrorCountInfo;
import edu.itu.wac.service.response.ErrorReportResponse;
import edu.itu.wac.service.response.ErrorReportWithoutSubpagesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ErrorReportService {

    List<ErrorReportResponse> getAll();

    List<ErrorReportWithoutSubpagesResponse> getAllWithoutSubpageInfo();

    List<ErrorReportResponse> findByWebsiteAddress(String address);

    List<ErrorReportWithoutSubpagesResponse> findByWebsiteAddressWithoutSubpages(String address);

    ErrorReportResponse findById(String id);

    List<ErrorCountInfo> getErrorCountInfo(String id);

    List<ErrorCountInfo> getErrorCountInfoByReport(ErrorReport errorReport);

    List<ComparisonResult> getComparisonData(ComparisonRequest comparisonRequest);

    ErrorReport save(ErrorReport errorReport);
}
