package edu.itu.wac.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class SubPageErrors {
    @Id
    String id;
    String subPage;
    String htmlPath;
    List<Error> errors = new ArrayList<>();
}
