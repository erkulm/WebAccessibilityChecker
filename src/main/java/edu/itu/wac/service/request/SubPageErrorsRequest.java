package edu.itu.wac.service.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubPageErrorsRequest {
  String id;
  String subPage;
  String htmlPath;
  List<ErrorRequest> errors = new ArrayList<>();
}
