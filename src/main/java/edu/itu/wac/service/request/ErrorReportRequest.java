package edu.itu.wac.service.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorReportRequest {
    String id;
    WebsiteRequest website;
    List<SubPageErrorsRequest> subPageErrors = new ArrayList<>();
    Integer totalErrors;
    Long reportGenerationTime;
}
