package edu.itu.wac.service.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WebsiteResponse {
    String id;
    String address;
    WebsiteCategoryResponse category;
    Integer clickCount;
    String fileName;
    Boolean isRead;
    String subUrl;
    LocalDateTime dateCreated;
}
