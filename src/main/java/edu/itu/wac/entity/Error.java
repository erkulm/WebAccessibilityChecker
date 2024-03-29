package edu.itu.wac.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Error {
  @Id
  @GeneratedValue(generator="uuid2")
  @GenericGenerator(name="uuid2", strategy = "uuid2")
  @Type(type="pg-uuid")
  String id;

  @JsonIgnoreProperties(value = "errors")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subpage_error_id", nullable = false)
  SubPageErrors subPageErrors;

  @OneToOne
  Website website;

  String subPage;
  String errorDesc;
  String errorScene;
  String document;
  String errorAddress;
  LocalDateTime testCrDate;
}
