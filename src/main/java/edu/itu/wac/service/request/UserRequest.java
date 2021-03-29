package edu.itu.wac.service.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {
    String id;
    @NotNull
    String username;
    @NotNull
    String password;
}
