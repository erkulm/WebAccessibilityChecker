package edu.itu.wac.service.response;

import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.request.WebsiteRequest;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class ErrorReportResponse {
    String id;
    WebsiteResponse website;
    List<SubPageErrorsResponse> subPageErrors = new ArrayList<>();
    LocalDateTime createdDate;
    Integer numberOfSubPages;
    Integer totalErrors;

}
