package edu.itu.wac.controller.web;

import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.response.ErrorReportResponse;
import edu.itu.wac.service.response.SubPageErrorsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class ReportDetailsController {
  private final ErrorReportService errorReportService;
  private final WebsiteService websiteService;

  public ReportDetailsController(
      ErrorReportService errorReportService, WebsiteService websiteService) {
    this.errorReportService = errorReportService;
    this.websiteService = websiteService;
  }

  @RequestMapping("/report-details")
  @ResponseBody
  public ModelAndView serveHistoryPage(
      @RequestParam(required = false) String id,
      @RequestParam(required = false) String filter,
      @RequestParam(required = false) String sort) {
    ModelAndView model = new ModelAndView();
    model.setViewName("report-details");
    log.info(id);

    ErrorReportResponse errorReport = errorReportService.findById(id);
    if (!StringUtils.isEmpty(sort)) {
      switch (sort) {
        case "address_asc" -> errorReport
                .getSubPageErrors()
                .sort(Comparator.comparing(SubPageErrorsResponse::getSubPage));
        case "address_desc" -> errorReport
                .getSubPageErrors()
                .sort(Comparator.comparing(SubPageErrorsResponse::getSubPage).reversed());
        case "error_asc" -> errorReport
                .getSubPageErrors()
                .sort(
                        Comparator.comparing(
                                subPageErrorsResponse -> subPageErrorsResponse.getErrors().size()));
        default -> {
          errorReport
                  .getSubPageErrors()
                  .sort(
                          Comparator.comparing(
                                  subPageErrorsResponse -> subPageErrorsResponse.getErrors().size()));
          Collections.reverse(errorReport.getSubPageErrors());
        }
      }
    }
    if (!StringUtils.isEmpty(filter)) {
      if ("has_error".equals(filter)) {
        errorReport.setSubPageErrors(
            errorReport.getSubPageErrors().stream()
                .filter(spe -> spe.getErrors().size() > 0)
                .collect(Collectors.toList()));
      } else if ("has_10".equals(filter)) {
        errorReport.setSubPageErrors(
            errorReport.getSubPageErrors().stream()
                .filter(spe -> spe.getErrors().size() > 10)
                .collect(Collectors.toList()));
      } else if ("has_25".equals(filter)) {
        errorReport.setSubPageErrors(
            errorReport.getSubPageErrors().stream()
                .filter(spe -> spe.getErrors().size() > 25)
                .collect(Collectors.toList()));
      }
    }
    model.addObject("errorReport", errorReport);
    model.addObject("message", "Hello World");
    return model;
  }
}
