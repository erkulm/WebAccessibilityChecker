package edu.itu.wac.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ErrorReport {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Type(type="pg-uuid")
  String id;

  @OneToOne Website website;

  @JsonIgnoreProperties(value = "errorReport")
  @OneToMany(mappedBy = "errorReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<SubPageErrors> subPageErrors = new ArrayList<>();

  @CreatedDate LocalDateTime createdDate;
  Integer numberOfSubPages;
  Integer totalErrors;
  Long reportGenerationTime;
}
