package edu.itu.wac.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

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
    String errorArea;
    String document;
    String websiteGroupId;
    @DBRef(lazy = true)
    WebsiteCategory category;
    String errorAddress;
    Timestamp testCrDate;
    Integer subPageErrorCount;
}
