package edu.itu.wac.entity;

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
public class ErrorReport {
    @Id
    String id;
    @DBRef(lazy = true)
    Website website;
    @DBRef(lazy = true)
    List<Error> errors = new ArrayList<>();
    @CreatedDate
    LocalDateTime createdDate;
    Integer numberOfSubPages;
}
