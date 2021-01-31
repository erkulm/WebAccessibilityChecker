package edu.itu.wac.service.response;

import lombok.Data;

import java.util.HashMap;

@Data
public class ErrorCountInfo {
    private String errorDesc;
    private String document;
    private Integer errorCount;
}
