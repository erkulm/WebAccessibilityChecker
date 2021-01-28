package edu.itu.wac.service.request;

import edu.itu.wac.entity.Error;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class SubPageErrorsRequest {
    String id;
    String subPage;
    List<ErrorRequest> errors = new ArrayList<>();
}
