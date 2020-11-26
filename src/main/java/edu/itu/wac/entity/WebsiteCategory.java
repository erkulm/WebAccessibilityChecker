package edu.itu.wac.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@TypeAlias("category")
@Data
@NoArgsConstructor
public class WebsiteCategory {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;

    public WebsiteCategory(String name){
        this.name = name;
    }
}
