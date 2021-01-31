package edu.itu.wac.service.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorReportResponse {
    String id;
    WebsiteResponse website;
    List<SubPageErrorsResponse> subPageErrors = new ArrayList<>();
    LocalDateTime createdDate;
    Integer numberOfSubPages;
    Integer totalErrors;
    Long reportGenerationTime;
}
