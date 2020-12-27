package edu.itu.wac.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Error {
    @Id
    String id;
    @DBRef(lazy = true)
    Website website;
    String subPage;
    String errorDesc;
    String errorScene;
    String document;
    String websiteGroupId;
    String errorAddress;
    @CreatedDate
    LocalDateTime testCrDate;
    String reportId;
}
