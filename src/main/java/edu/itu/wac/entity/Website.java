package edu.itu.wac.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class Website {
    @Id
    String id;
    String address;
    @DBRef(lazy = true)
    WebsiteCategory category;
    Integer clickCount;
    String fileName;
    Boolean isRead;
    String subUrl;
    @CreatedDate
    LocalDateTime dateCreated;
    @LastModifiedDate
    LocalDateTime dateModified;
    LocalDateTime latestTestDate;
    public Website(String name){
        this.address = name;
    }
}
