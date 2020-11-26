package edu.itu.wac.service.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WebsiteCategoryRequest {
    private String id;
    @NotNull
    private String name;
    private String description;
}
