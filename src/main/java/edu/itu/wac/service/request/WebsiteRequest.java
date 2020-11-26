package edu.itu.wac.service.request;

import edu.itu.wac.entity.WebsiteCategory;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class WebsiteRequest {
    String id;
    @NotNull
    String address;
    WebsiteCategory category;
    Integer clickCount;
    String fileName;
    Boolean isRead;
    String subUrl;
    LocalDateTime dateCreated;
}
