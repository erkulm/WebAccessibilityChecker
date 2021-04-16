package edu.itu.wac.service.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    String id;
    String username;
    String password;
    List<PermissionResponse> permissions;
}
