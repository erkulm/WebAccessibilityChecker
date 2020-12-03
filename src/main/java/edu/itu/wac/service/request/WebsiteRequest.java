package edu.itu.wac.service.request;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class WebsiteRequest {
    String id;
    @NotNull
    String address;
    WebsiteCategoryRequest category;
    Integer clickCount;
    String fileName;
    Boolean isRead;
    String subUrl;
    @CreatedDate
    LocalDateTime dateCreated;
}
