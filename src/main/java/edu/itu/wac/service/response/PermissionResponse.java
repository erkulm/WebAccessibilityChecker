package edu.itu.wac.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionResponse {
    String id;
    String name;
    @JsonIgnoreProperties(value = {"permissions"})
    Set<UserResponse> users;
}
