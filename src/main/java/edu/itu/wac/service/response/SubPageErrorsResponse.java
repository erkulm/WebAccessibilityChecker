package edu.itu.wac.service.response;

import edu.itu.wac.service.request.ErrorRequest;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class SubPageErrorsResponse {
    String id;
    String subPage;
    List<ErrorRequest> errors = new ArrayList<>();
}
