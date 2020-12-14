package edu.itu.wac.controller.rest;

import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ErrorRestController {
    private final ErrorService errorService;

    @Autowired
    public ErrorRestController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping("/error-report")
    @ResponseBody
    List<ErrorResponse> getWebsiteByAddress(@RequestParam @NotNull String address) {
        return errorService.findByWebsiteAddress(address);
    }

    @GetMapping("/generate-report")
    @ResponseBody
    List<ErrorResponse> generateNewReport(@RequestParam @NotNull String address) {
        return errorService.generateReport(address);
    }
}
