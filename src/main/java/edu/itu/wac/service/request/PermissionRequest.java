package edu.itu.wac.service.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class PermissionRequest {
    String id;
    @NotNull
    String name;
    Set<UserRequest> users;
}
