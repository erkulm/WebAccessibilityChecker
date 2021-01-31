package edu.itu.wac.service.response;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class SubPageErrorsResponse {
    String id;
    String htmlPath;
    String subPage;
    List<ErrorResponse> errors = new ArrayList<>();
}
