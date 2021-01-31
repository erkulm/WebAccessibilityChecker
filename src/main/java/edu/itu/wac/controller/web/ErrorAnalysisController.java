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
        model.addObject("errorReport", errorReport);
        model.addObject("errorCountInfoList", errorCountInfoList);
        return model;
    }

}
