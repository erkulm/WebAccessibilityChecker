package edu.itu.wac.controller.web;


import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import edu.itu.wac.util.Pa11yUtil;
import ma.glasnost.orika.MapperFacade;
import edu.itu.wac.model.ErrorCategory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DetailController {
    @Autowired
    WebsiteService websiteService;

    @Autowired
    @Qualifier(value = "websiteServiceMapper")
    MapperFacade mapperFacade;

    @Autowired
    ErrorService errorService;

    @RequestMapping("/detail")
    @ResponseBody
    public ModelAndView serveDetailPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("detail");
        String url = "https://en.wikipedia.org/wiki/OpenCV";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e){
            System.err.println("Error in given url!");
        }


	/*	DBConnection connection = new DBConnection();
		ErrorReport errorDefinitions = new ErrorReport();
		errorDefinitions.setConn(connection.conn);
		Vector<ErrorReport> errorDefList = null;
		try {
			errorDefList =  errorDefinitions.fetchWebsiteErrorDefinitons(996);
		} catch (SQLException e) {
			e.printStackTrace();
		} */

        ArrayList<ErrorResponse> errorDefList = new ArrayList<>();



        WebsiteResponse websiteResponse = websiteService.findByAddress(url);
        if (websiteResponse == null) {
            websiteResponse = websiteService.createNewWebsiteFromAddress(url);
        }
        List<ErrorResponse> errorResponses;
        if (websiteResponse.getLatestTestDate() == null
                || websiteResponse.getLatestTestDate().plusDays(1).isBefore(LocalDateTime.now())) {
            List<Error> errors = Pa11yUtil.runPa11y(
                    mapperFacade.map(websiteResponse, Website.class),
                    "", mapperFacade.map(websiteResponse.getCategory(), WebsiteCategory.class));

            errorResponses = errorService.saveAll(mapperFacade.mapAsList(errors, ErrorRequest.class));
            websiteService.updateLatestTestDate(url);
        } else {
            errorResponses = errorService.findByWebsiteAddress(url);
        }
        errorDefList.addAll(errorResponses);
        int j = 0;
        List<ErrorCategory> errorCategory = new ArrayList<>();
        for(int i = 0; i < errorDefList.size(); i++)
        {
            String header = errorDefList.get(i).getDocument();
            String desc = errorDefList.get(i).getErrorDesc();
            String id = errorDefList.get(i).getId();
            ErrorCategory errorCat = new ErrorCategory();
            errorCat.setErrorId(id);
            errorCat.setHeader(header);
            errorCat.setErrorDesc(desc);
            errorCategory.add(errorCat);
            Elements errors = doc.select(errorDefList.get(i).getErrorAddress());

            for (Element error : errors) {


                error.parent().addClass("item");
                error.addClass("error");
                String errorCode = "error" + (j + 1);
                error.attr("id",errorCode);
                error.parent().prepend("<div class='info'>"
                        + "<i class=\"fas fa-exclamation-triangle infoPng\"></i>"
                        + "<a class='infoMsg'><span class='msg'>"+ "ERROR"
                        + "</span><div class='detail'>"+ errorDefList.get(i).getErrorDesc() +"</div></a>"
                        + "</div>");
                j++;
            }
        }

        String pageHtml = doc.outerHtml();



        model.addObject("errorlist", errorDefList);
        model.addObject("errorCategory", errorCategory);

        Document docHtml = Jsoup.parse(pageHtml);
        model.addObject("html", docHtml);
        model.addObject("pageHtml", pageHtml);

        model.addObject("message", "Hello World");
        return model;
    }

}
