package edu.itu.wac.controller.rest;

import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.response.ErrorReportResponse;
import edu.itu.wac.service.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ErrorReportRestController {
    private final ErrorReportService errorReportService;

    @Autowired
    public ErrorReportRestController(ErrorReportService errorReportService) {
        this.errorReportService = errorReportService;
    }

    @GetMapping("/error-report-by-website")
    @ResponseBody
    List<ErrorReportResponse> getWebsiteByAddress(@RequestParam @NotNull String address) {
        return errorReportService.findByWebsiteAddress(address);
    }
}
