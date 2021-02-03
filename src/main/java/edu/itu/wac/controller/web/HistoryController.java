package edu.itu.wac.controller.web;

import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.response.ErrorReportResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class HistoryController {
  private final ErrorReportService errorReportService;
  private final WebsiteService websiteService;

  public HistoryController(ErrorReportService errorReportService, WebsiteService websiteService) {
    this.errorReportService = errorReportService;
    this.websiteService = websiteService;
  }

  @RequestMapping("/history")
  @ResponseBody
  public ModelAndView serveHistoryPage(
      @RequestParam(required = false) String website,
      @RequestParam(required = false) String filter,
      @RequestParam(required = false) String sort) {
    ModelAndView model = new ModelAndView();
    model.setViewName("history");
    log.info(website);

    List<ErrorReportResponse> errorReports = errorReportService.findByWebsiteAddress(website);
    List<String> websites =
        websiteService.getAll().stream()
            .map(WebsiteResponse::getAddress)
            .collect(Collectors.toList());

    if (!StringUtils.isEmpty(sort)) {
      if ("date_asc".equals(sort)) {
        errorReports.sort(Comparator.comparing(ErrorReportResponse::getCreatedDate).reversed());
      } else if ("date_desc".equals(sort)) {
        errorReports.sort(Comparator.comparing(ErrorReportResponse::getCreatedDate));
      } else if ("error_asc".equals(sort)) {
        errorReports.sort(Comparator.comparing(ErrorReportResponse::getTotalErrors));
      } else {
        errorReports.sort(Comparator.comparing(ErrorReportResponse::getTotalErrors).reversed());
      }
    }

    if (!StringUtils.isEmpty(filter)) {
      if ("last_week".equals(filter)) {
        errorReports =
            errorReports.stream()
                .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusWeeks(1)))
                .collect(Collectors.toList());
      } else if ("last_month".equals(filter)) {
        errorReports =
            errorReports.stream()
                .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusMonths(1)))
                .collect(Collectors.toList());
      } else if ("six_months".equals(filter)) {
        errorReports =
            errorReports.stream()
                .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusMonths(6)))
                .collect(Collectors.toList());
      } else if ("last_year".equals(filter)) {
        errorReports =
            errorReports.stream()
                .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusYears(1)))
                .collect(Collectors.toList());
      } else {
        errorReports =
            errorReports.stream()
                .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusYears(2)))
                .collect(Collectors.toList());
      }
    }

    model.addObject("errorReports", errorReports);
    model.addObject("websiteList", websites);
    model.addObject("website", website);
    model.addObject("message", "Hello World");
    return model;
  }
}
