package edu.itu.wac.service.response;

import edu.itu.wac.entity.WebsiteCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WebsiteResponse {
    String id;
    String address;
    WebsiteCategory category;
    Integer clickCount;
    String fileName;
    Boolean isRead;
    String subUrl;
    LocalDateTime dateCreated;
}
