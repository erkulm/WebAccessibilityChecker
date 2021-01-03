package edu.itu.wac.service.request;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.Website;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class ErrorReportRequest {
    String id;
    WebsiteRequest website;
    List<ErrorRequest> errors = new ArrayList<>();
}
