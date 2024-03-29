package edu.itu.wac.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Permission implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Type(type="pg-uuid")
  String id;

  @JsonProperty(value = "users")
  @JsonIgnoreProperties(value = {"permissions"})
  @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
  private Set<User> users;

  String name;
}
