package edu.itu.wac.controller.web;

import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.response.ErrorReportWithoutSubpagesResponse;
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

    List<ErrorReportWithoutSubpagesResponse> errorReports =
            errorReportService.findByWebsiteAddressWithoutSubpages(website);
    List<String> websites =
        websiteService.getAll().stream()
            .map(WebsiteResponse::getAddress)
            .collect(Collectors.toList());

    if (!StringUtils.isEmpty(sort)) {
      switch (sort) {
        case "date_asc":
          errorReports.sort(Comparator.comparing(ErrorReportWithoutSubpagesResponse::getCreatedDate).reversed());
          break;
        case "date_desc":
          errorReports.sort(Comparator.comparing(ErrorReportWithoutSubpagesResponse::getCreatedDate));
          break;
        case "error_asc":
          errorReports.sort(Comparator.comparing(ErrorReportWithoutSubpagesResponse::getTotalErrors));
          break;
        default:
          errorReports.sort(Comparator.comparing(ErrorReportWithoutSubpagesResponse::getTotalErrors).reversed());
          break;
      }
    }

    if (!StringUtils.isEmpty(filter)) {
      switch (filter) {
        case "last_week":
          errorReports =
                  errorReports.stream()
                          .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusWeeks(1)))
                          .collect(Collectors.toList());
          break;
        case "last_month":
          errorReports =
                  errorReports.stream()
                          .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusMonths(1)))
                          .collect(Collectors.toList());
          break;
        case "six_months":
          errorReports =
                  errorReports.stream()
                          .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusMonths(6)))
                          .collect(Collectors.toList());
          break;
        case "last_year":
          errorReports =
                  errorReports.stream()
                          .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusYears(1)))
                          .collect(Collectors.toList());
          break;
        default:
          errorReports =
                  errorReports.stream()
                          .filter(er -> er.getCreatedDate().isAfter(LocalDateTime.now().minusYears(2)))
                          .collect(Collectors.toList());
          break;
      }
    }

    model.addObject("errorReports", errorReports);
    model.addObject("websiteList", websites);
    model.addObject("website", website);
    model.addObject("message", "Hello World");
    return model;
  }
}
