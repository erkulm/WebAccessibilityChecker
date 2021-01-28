package edu.itu.wac.controller.web;


import edu.itu.wac.model.ErrorCategory;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.response.ErrorResponse;
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

@Controller
public class IndexController {
    private final ErrorService errorService;

    public IndexController(ErrorService errorService){
    this.errorService = errorService;
    }

    @RequestMapping("/index")
    @ResponseBody
    public ModelAndView serveDetailPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

}
