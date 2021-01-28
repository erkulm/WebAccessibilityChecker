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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class ReportDetailsController {
    private final ErrorReportService errorReportService;
    private final WebsiteService websiteService;

    public ReportDetailsController(ErrorReportService errorReportService, WebsiteService websiteService) {
        this.errorReportService = errorReportService;
        this.websiteService = websiteService;
    }

    @RequestMapping("/report-details")
    @ResponseBody
    public ModelAndView serveHistoryPage(@RequestParam(required = false) String id, @RequestParam(required = false) String f, @RequestParam(required = false) String sort) {
        ModelAndView model = new ModelAndView();
        model.setViewName("report-details");
        log.info(id);

        ErrorReportResponse errorReport = errorReportService.findById(id);
        model.addObject("errorReport", errorReport);
        model.addObject("message", "Hello World");
        return model;
    }

}
