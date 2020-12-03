package edu.itu.wac.service.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ErrorRequest {
    String id;
    WebsiteRequest website;
    String subPage;
    String errorDesc;
    String errorScene;
    String errorArea;
    String document;
    String websiteGroupId;
    WebsiteCategoryRequest category;
    String errorAddress;
    Timestamp testCrDate;
    Integer subPageErrorCount;
}
