package edu.itu.wac.service.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PermissionRequest {
    String id;
    @NotNull
    String name;
}
