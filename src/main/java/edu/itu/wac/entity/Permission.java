package edu.itu.wac.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Permission {
    @Id
    String id;
    String name;
}
