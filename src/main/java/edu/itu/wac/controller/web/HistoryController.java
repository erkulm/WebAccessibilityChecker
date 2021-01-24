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
public class HistoryController {
    private final ErrorReportService errorReportService;
    private final WebsiteService websiteService;

    public HistoryController(ErrorReportService errorReportService, WebsiteService websiteService) {
        this.errorReportService = errorReportService;
        this.websiteService = websiteService;
    }

    @RequestMapping("/history")
    @ResponseBody
    public ModelAndView serveHistoryPage(@RequestParam(required = false) String website, @RequestParam(required = false) String f, @RequestParam(required = false) String sort) {
        ModelAndView model = new ModelAndView();
        model.setViewName("history");
        log.info(website);

        List<ErrorReportResponse> errorReports = errorReportService.findByWebsiteAddress(website);
        List<String> websites = websiteService.getAll()
                .stream()
                .map(WebsiteResponse::getAddress)
                .collect(Collectors.toList());

        if (!StringUtils.isEmpty(sort)){
            if ("error_asc".equals(sort)) {
                errorReports.sort(Comparator.comparing(ErrorReportResponse::getCreatedDate));
            }else{
                errorReports.sort(Comparator.comparing(ErrorReportResponse::getCreatedDate).reversed());
            }
        }

        model.addObject("errorReports", errorReports);
        model.addObject("websiteList", websites);
        model.addObject("website", website);
        model.addObject("message", "Hello World");
        return model;
    }

}
