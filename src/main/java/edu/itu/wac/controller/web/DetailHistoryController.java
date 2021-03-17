package edu.itu.wac.controller.web;


import edu.itu.wac.model.ErrorCategory;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.SubPageErrorsService;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.SubPageErrorsResponse;
import edu.itu.wac.util.ZipUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DetailHistoryController {
    private final SubPageErrorsService subPageErrorsService;

    public DetailHistoryController(SubPageErrorsService subPageErrorsService){
    this.subPageErrorsService = subPageErrorsService;
    }

    @GetMapping("/detail-history")
    @ResponseBody
    public ModelAndView serveDetailPage(@RequestParam(required = false) String subPageErrorsId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("detail-history");

        SubPageErrorsResponse subPageErrorsResponse = subPageErrorsService.findById(subPageErrorsId);

        Document doc = null;
        try {
            byte[] htmlBytes = ZipUtil.unzip(Files.readAllBytes(Paths.get(subPageErrorsResponse.getHtmlPath())));
            doc = Jsoup.parse(new String(htmlBytes));
        } catch (Exception e) {
            System.err.println("Error in given url!");
        }

        List<ErrorResponse> errorDefList = subPageErrorsResponse.getErrors();

        List<ErrorCategory> errorCategory = new ArrayList<>();
        for (ErrorResponse errorResponse : errorDefList) {
            String header = errorResponse.getDocument();
            String desc = errorResponse.getErrorDesc();
            String id = errorResponse.getId();
            ErrorCategory errorCat = new ErrorCategory();
            errorCat.setErrorId("error"+id);
            errorCat.setHeader(header);
            errorCat.setErrorDesc(desc);
            errorCategory.add(errorCat);
            Elements errors = doc.select(errorResponse.getErrorAddress());

            for (Element error : errors) {
                error.parent().addClass("item");
                error.addClass("error");
                error.attr("id", "error"+errorResponse.getId());
                error.parent().prepend("<div class='info' id=\"info_"+errorResponse.getId()+"\">"
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
