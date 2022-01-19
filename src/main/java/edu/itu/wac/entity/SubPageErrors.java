package edu.itu.wac.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SubPageErrors {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Type(type="pg-uuid")
  String id;

  @JsonIgnoreProperties(value = "subPageErrors")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "error_report_id", nullable = false)
  ErrorReport errorReport;

  String subPage;
  String htmlPath;
  @JsonIgnoreProperties(value = "subPageErrors")
  @OneToMany(mappedBy = "subPageErrors", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Error> errors = new ArrayList<>();
}
