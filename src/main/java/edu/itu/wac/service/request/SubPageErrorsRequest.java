package edu.itu.wac.service.request;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class SubPageErrorsRequest {
    String id;
    String subPage;
    String pageHtml;
    List<ErrorRequest> errors = new ArrayList<>();
}
