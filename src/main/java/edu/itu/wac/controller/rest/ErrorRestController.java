package edu.itu.wac.controller.rest;

import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.response.ErrorResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.FileOutputStream;
import java.io.IOException;
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
    List<ErrorResponse> generateNewReport(@RequestParam @NotNull String address, @RequestParam @NotNull  Boolean isHtmlHistoryEnabled) {
        return errorService.generateReport(address, isHtmlHistoryEnabled);
    }

    @GetMapping("/generate-deep-report")
    @ResponseBody
    List<ErrorResponse> generateDeepReport(@RequestParam @NotNull String address, @RequestParam @NotNull  Boolean isHtmlHistoryEnabled) {
        return errorService.generateDeepReport(address, isHtmlHistoryEnabled);
    }

    @GetMapping(value = "/errors.xlsx")
    public void getExcelFile(HttpServletResponse response) {
        try {
            List<ErrorResponse> fareRateOutputList = errorService.getAll();
            Workbook workbook = errorService.generateExcel(fareRateOutputList);
            workbook.write(response.getOutputStream());
            response.flushBuffer();
            FileOutputStream fileOut = new FileOutputStream("Macintosh HD/Users/mahmut/Downloads/ferrors.xslsx");
            workbook.write(fileOut);
            fileOut.close();
            response.addHeader("Content-disposition", "attachment; filename=farerates.xlsx");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
