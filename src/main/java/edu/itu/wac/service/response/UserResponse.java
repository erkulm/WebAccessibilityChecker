package edu.itu.wac.service.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponse {
    String id;
    String username;
    @JsonIgnore
    String password;
    @JsonIgnoreProperties(value = {"users"})
    List<PermissionResponse> permissions = new ArrayList<>();
}
