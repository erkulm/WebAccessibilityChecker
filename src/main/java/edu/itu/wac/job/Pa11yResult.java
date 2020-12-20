package edu.itu.wac.job;

import edu.itu.wac.entity.Error;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pa11yResult {
    List<Error> errors = new ArrayList<>();
}
