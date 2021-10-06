package edu.itu.wac.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "USERS")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  private String username;
  private String password;

  @JsonProperty(value = "permissions")
  @JsonIgnoreProperties(value = {"users"})
  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_permission",
      joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
      inverseJoinColumns = {@JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID")})
  private List<Permission> permissions;
}
