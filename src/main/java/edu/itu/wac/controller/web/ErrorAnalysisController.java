package edu.itu.wac.controller.web;


import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.SubPageErrorsService;
import edu.itu.wac.service.response.ErrorCountInfo;
import edu.itu.wac.service.response.ErrorReportResponse;
import lombok.extern.slf4j.Slf4j;
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
    private final SubPageErrorsService subPageErrorsService;
    private final ErrorReportService errorReportService;

    public ErrorAnalysisController(SubPageErrorsService subPageErrorsService, ErrorReportService errorReportService) {
        this.subPageErrorsService = subPageErrorsService;
        this.errorReportService = errorReportService;
    }

    @RequestMapping("/error-analysis")
    @ResponseBody
    public ModelAndView serveHistoryPage(@RequestParam(required = false) String id, @RequestParam(required = false) String f, @RequestParam(required = false) String sort) {
        ModelAndView model = new ModelAndView();
        model.setViewName("error-analysis");
        ErrorReportResponse errorReport = errorReportService.findById(id);
        List<ErrorCountInfo> errorCountInfoList = errorReportService.getErrorCountInfo(id);
        List<ErrorReportResponse> reportsFromSameWebsite = errorReportService.findByWebsiteAddress(errorReport.getWebsite().getAddress());
        List<ErrorReportResponse> allReports = errorReportService.getAll().stream()
                .filter(r -> r.getWebsite() != null && r.getWebsite().getAddress() != null).collect(Collectors.toList());

        model.addObject("errorReport", errorReport);
        model.addObject("errorCountInfoList", errorCountInfoList);
        model.addObject("barChartData", errorCountInfoList.stream().limit(5L).map(ErrorCountInfo::getErrorCount).collect(Collectors.toList()));
        model.addObject("website", errorReport.getWebsite().getAddress());
        model.addObject("reportsFromSameWebsite", reportsFromSameWebsite);
        model.addObject("allReports", allReports);
        model.addObject("reportsFromSameIndustry", allReports);
        return model;
    }

}
