package edu.itu.wac.controller.rest;

import edu.itu.wac.model.ErrorCategory;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.response.ErrorResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class IFrameRestController {

    @Autowired
    ErrorService errorService;

    @GetMapping("/iframe-content")
    @ResponseBody
    String getIFrameContent(@RequestParam @NotNull String address) {

        Document doc = null;
        try {
            doc = Jsoup.connect(address).get();
        } catch (IOException e) {
            return "Website could not be read";
        }
        List<ErrorResponse> errorDefList = errorService.generateReport(address);

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

        doc.head()
                .appendElement("script")
                .attr("type", "text/javascript")
                .appendChild(new DataNode(getJavaScript()));

        String pageHtml = doc.outerHtml();
        Document docHtml = Jsoup.parse(pageHtml);
        return pageHtml;
    }

    public String getJavaScript() {
        String javaScript = "window.addEventListener('message', event => {\n" +
                "    // IMPORTANT: check the origin of the data! \n" +
                "    if (event.origin.startsWith('http://localhost')) { \n" +
                "    console.log(event.data) \n" +
                "   let element = document.getElementById(event.data);\n" +
                "      \n" +
                "      if (element != null) {\n" +
                "        element.scrollIntoView({\n" +
                "          behavior: \"smooth\",\n" +
                "          block: \"center\",\n" +
                "          inline: \"nearest\",\n" +
                "        });\n" +
                "\n" +
                "        element.style.border = \"5px dashed #F76C6C\";\n" +
                "        setTimeout(() => {\n" +
                "          element.border = \"none\";\n" +
                "        }, 2000);\n" +
                "      }" +
                "        console.log(event.data); \n" +
                "    } else {\n" +
                "        // The data was NOT sent from your site! \n" +
                "        // Be careful! Do not use it. This else branch is\n" +
                "        // here just for clarity, you usually shouldn't need it.\n" +
                "        return; \n" +
                "    } \n" +
                "}); ";
        return javaScript;
    }
}
