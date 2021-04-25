package edu.itu.wac.service.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorReportWithoutSubpagesResponse {
    String id;
    WebsiteResponse website;
    LocalDateTime createdDate;
    Integer numberOfSubPages;
    Integer totalErrors;
    Long reportGenerationTime;
}
