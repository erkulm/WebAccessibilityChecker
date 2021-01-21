package edu.itu.wac.service.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ErrorRequest {
    String id;
    WebsiteRequest website;
    String subPage;
    String errorDesc;
    String errorScene;
    String errorArea;
    String document;
    String errorAddress;
    LocalDateTime testCrDate;
}
