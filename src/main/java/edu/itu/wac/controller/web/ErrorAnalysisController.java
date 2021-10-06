package edu.itu.wac.controller.web;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.response.ErrorCountInfo;
import edu.itu.wac.service.response.ErrorReportResponse;
import edu.itu.wac.service.response.ErrorReportWithoutSubpagesResponse;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class ErrorAnalysisController {
  private final ErrorReportService errorReportService;
  private final MapperFacade mapperFacade;

  public ErrorAnalysisController(
      ErrorReportService errorReportService,
      @Qualifier(value = "errorReportServiceMapper") MapperFacade mapperFacade) {
    this.errorReportService = errorReportService;
    this.mapperFacade = mapperFacade;
  }

  @RequestMapping("/error-analysis")
  @ResponseBody
  public ModelAndView serveHistoryPage(
      @RequestParam(required = false) String id,
      @RequestParam(required = false) String f,
      @RequestParam(required = false) String sort) {
    ModelAndView model = new ModelAndView();
    model.setViewName("error-analysis");
    ErrorReportResponse errorReport = errorReportService.findById(id);
    List<ErrorCountInfo> errorCountInfoList =
        errorReportService.getErrorCountInfoByReport(
            mapperFacade.map(errorReport, ErrorReport.class));
    List<ErrorReportWithoutSubpagesResponse> reportsFromSameWebsite =
        errorReportService.findByWebsiteAddressWithoutSubpages(
            errorReport.getWebsite().getAddress());
    List<ErrorReportWithoutSubpagesResponse> allReports =
        errorReportService.getAllWithoutSubpageInfo().stream()
            .filter(r -> r.getWebsite() != null && r.getWebsite().getAddress() != null)
            .collect(Collectors.toList());

    model.addObject("errorReport", errorReport);
    model.addObject("errorCountInfoList", errorCountInfoList);
    model.addObject(
        "barChartData",
        errorCountInfoList.stream()
            .limit(5L)
            .map(ErrorCountInfo::getErrorCount)
            .collect(Collectors.toList()));
    model.addObject("website", errorReport.getWebsite().getAddress());
    model.addObject("reportsFromSameWebsite", reportsFromSameWebsite);
    model.addObject("allReports", allReports);
    model.addObject("reportsFromSameIndustry", allReports);
    return model;
  }
}
