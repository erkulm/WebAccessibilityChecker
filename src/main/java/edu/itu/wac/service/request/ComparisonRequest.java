package edu.itu.wac.service.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ComparisonRequest {
    List<String> reportIds;
}
