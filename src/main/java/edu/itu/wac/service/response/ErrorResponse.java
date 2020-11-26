package edu.itu.wac.service.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ErrorResponse {
    String id;
    WebsiteResponse website;
    String subPage;
    String errorDesc;
    String errorScene;
    String errorArea;
    String document;
    String websiteGroupId;
    WebsiteCategoryResponse category;
    String errorAddress;
    Timestamp testCrDate;
    Integer subPageErrorCount;
}
