package edu.itu.wac.service.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ComparisonResult {
    private Map<String, Long> data = new HashMap<>();
    String address;
}
