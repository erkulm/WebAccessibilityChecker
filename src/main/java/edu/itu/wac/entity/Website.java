package edu.itu.wac.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
@Data
@NoArgsConstructor
public class Website {
  @Id
  @GeneratedValue(generator="uuid2")
  @GenericGenerator(name="uuid2", strategy = "uuid2")
  @Type(type="pg-uuid")
  String id;

  String address;

  @OneToOne
  WebsiteCategory category;

  Integer clickCount;
  String fileName;
  Boolean isRead;
  String subUrl;
  @CreatedDate LocalDateTime dateCreated;
  @LastModifiedDate LocalDateTime dateModified;
  LocalDateTime latestTestDate;

  public Website(String name) {
    this.address = name;
  }
}
