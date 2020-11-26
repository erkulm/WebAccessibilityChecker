package edu.itu.wac.service.request;

import edu.itu.wac.service.response.WebsiteCategoryResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ErrorRequest {
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
