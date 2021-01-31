package edu.itu.wac.service.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubPageErrorsResponse {
    String id;
    String htmlPath;
    String subPage;
    List<ErrorResponse> errors = new ArrayList<>();
}
