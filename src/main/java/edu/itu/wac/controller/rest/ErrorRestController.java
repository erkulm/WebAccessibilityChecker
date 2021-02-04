package edu.itu.wac.controller.rest;

import edu.itu.wac.entity.Error;
import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.SubPageErrorsService;
import edu.itu.wac.service.response.ErrorReportResponse;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.SubPageErrorsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URI;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Slf4j
public class ErrorRestController {
    private final ErrorService errorService;
    private final ErrorReportService errorReportService;
    private final SubPageErrorsService subPageErrorsService;

    @Autowired
    public ErrorRestController(ErrorService errorService,ErrorReportService errorReportService,SubPageErrorsService subPageErrorsService) {
        this.errorService = errorService;
        this.errorReportService = errorReportService;
        this.subPageErrorsService = subPageErrorsService;
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
      // FileOutputStream fileOut = new FileOutputStream("Macintosh
      // HD/Users/mahmut/Downloads/ferrors.xslsx");
      FileOutputStream fileOut =
          new FileOutputStream(
              "C:\\Users\\Kafein\\Downloads\\data\\" + UUID.randomUUID().toString() + ".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            response.addHeader("Content-disposition", "attachment; filename=farerates.xlsx");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping(
            value = "/get-file",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody byte[] getFile(@RequestParam String id, @RequestParam String type, HttpServletResponse response) throws IOException {
      List<ErrorResponse> errors = null;
      String fileAddress = null;
      try {
            if ("report".equals(type)){
              ErrorReportResponse errorReport = errorReportService.findById(id);
              errors = errorReport.getSubPageErrors()
                      .stream()
                      .flatMap(spe -> spe.getErrors().stream())
                      .collect(Collectors.toList());
            }else{
              SubPageErrorsResponse subPageErrorsResponse = subPageErrorsService.findById(id);
              errors = subPageErrorsResponse.getErrors();
            }
            Workbook workbook = errorService.generateExcel(errors);
            workbook.write(response.getOutputStream());
            response.flushBuffer();
            fileAddress = "C:\\Users\\Kafein\\Downloads\\data\\" + UUID.randomUUID().toString() + ".xlsx";
            FileOutputStream fileOut =new FileOutputStream(fileAddress);
            workbook.write(fileOut);

            fileOut.close();
            response.addHeader("Content-disposition", "attachment; filename=farerates.xlsx");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
      byte[] bytes = null;
        try{
          File file = new File(fileAddress);
          InputStream inputStream = new FileInputStream(file);
          bytes = IOUtils.toByteArray(inputStream);
          inputStream.close();
        }catch (Exception e){
          log.error(ExceptionUtils.getStackTrace(e));
        }
      return bytes;
    }
}
