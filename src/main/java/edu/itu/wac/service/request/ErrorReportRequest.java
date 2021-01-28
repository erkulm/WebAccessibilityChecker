package edu.itu.wac.service.request;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class ErrorReportRequest {
    String id;
    WebsiteRequest website;
    List<SubPageErrorsRequest> subPageErrors = new ArrayList<>();
    Integer totalErrors;
}
