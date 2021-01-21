package edu.itu.wac.controller.web;


import edu.itu.wac.model.ErrorCategory;
import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.response.ErrorReportResponse;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HistoryController {
    private final ErrorReportService errorReportService;
    private final WebsiteService websiteService;

    public HistoryController(ErrorReportService errorReportService, WebsiteService websiteService) {
        this.errorReportService = errorReportService;
        this.websiteService = websiteService;
    }

    @RequestMapping("/history")
    @ResponseBody
    public ModelAndView serveHistoryPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("history");
        String url = "https://www.vodafone.com.tr";

        List<ErrorReportResponse> errorReports = errorReportService.findByWebsiteAddress(url);
        List<String> websites = websiteService.getAll()
                .stream()
                .map(WebsiteResponse::getAddress)
                .collect(Collectors.toList());

        model.addObject("errorReports", errorReports);
        model.addObject("websiteList", websites);
        model.addObject("message", "Hello World");
        return model;
    }

}
