package edu.itu.wac.controller.web;


import edu.itu.wac.model.ErrorCategory;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.response.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DetailController {
    private final ErrorService errorService;

    public DetailController(ErrorService errorService){
    this.errorService = errorService;
    }

    @PostMapping("/detail")
    @ResponseBody
    public ModelAndView serveDetailPage(@RequestParam(required = false) String url, @RequestParam(required = false) String deep) {
        ModelAndView model = new ModelAndView();
        model.setViewName("detail");
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.err.println("Error in given url!");
        }

        List<ErrorResponse> errorDefList = null;

        if (!StringUtils.isEmpty(deep)){
          if ("on".equals(deep)){
              List<ErrorResponse> errorResponses = errorService.generateDeepReport(url, false);
             errorDefList= errorResponses.stream().filter(er->url.equals(er.getSubPage())).collect(Collectors.toList());
          }
        }else{
          errorDefList = errorService.generateReport(url,false);
        }

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
                error.attr("id", "error"+errorResponse.getId());
                error.parent().prepend("<div class='info' id=\"info_"+errorResponse.getId()+"\">"
                        + "<i class=\"fas fa-exclamation-triangle infoPng\"></i>"
                        + "<a class='infoMsg'><span class='msg'>" + "ERROR"
                        + "</span><div class='detail'>" + errorResponse.getErrorDesc() + "</div></a>"
                        + "</div>");
            }
        }

        if (!errorDefList.isEmpty()){
            String reportId = errorService.findErrorReportByErrorId(errorDefList.iterator().next().getId());
            model.addObject("report_id", reportId);
        }

        String pageHtml = doc.outerHtml();
        Document docHtml = Jsoup.parse(pageHtml);

        model.addObject("errorlist", errorDefList);
        model.addObject("errorCategory", errorCategory);

        model.addObject("html", docHtml);
        model.addObject("pageHtml", pageHtml);

        return model;
    }

}
