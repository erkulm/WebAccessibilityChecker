package edu.itu.wac.controller.web;


import edu.itu.wac.model.ErrorCategory;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.response.ErrorResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DetailController {
    private final ErrorService errorService;

    public DetailController(ErrorService errorService){
    this.errorService = errorService;
    }

    @PostMapping("/detail")
    @ResponseBody
    public ModelAndView serveDetailPage(@RequestParam(required = false) String url) {
        ModelAndView model = new ModelAndView();
        model.setViewName("detail");
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.err.println("Error in given url!");
        }

        List<ErrorResponse> errorDefList = errorService.generateReport(url);

        List<ErrorCategory> errorCategory = new ArrayList<>();
        for (ErrorResponse errorResponse : errorDefList) {
            String header = errorResponse.getDocument();
            String desc = errorResponse.getErrorDesc();
            String id = errorResponse.getId();
            ErrorCategory errorCat = new ErrorCategory();
            errorCat.setErrorId(id);
            errorCat.setHeader(header);
            errorCat.setErrorDesc(desc);
            errorCategory.add(errorCat);
            Elements errors = doc.select(errorResponse.getErrorAddress());

            for (Element error : errors) {
                error.parent().addClass("item");
                error.addClass("error");
                error.attr("id", errorResponse.getId());
                error.parent().prepend("<div class='info'>"
                        + "<i class=\"fas fa-exclamation-triangle infoPng\"></i>"
                        + "<a class='infoMsg'><span class='msg'>" + "ERROR"
                        + "</span><div class='detail'>" + errorResponse.getErrorDesc() + "</div></a>"
                        + "</div>");
            }
        }

        String pageHtml = doc.outerHtml();
        Document docHtml = Jsoup.parse(pageHtml);

        model.addObject("errorlist", errorDefList);
        model.addObject("errorCategory", errorCategory);

        model.addObject("html", docHtml);
        model.addObject("pageHtml", pageHtml);

        model.addObject("message", "Hello World");
        return model;
    }

}
