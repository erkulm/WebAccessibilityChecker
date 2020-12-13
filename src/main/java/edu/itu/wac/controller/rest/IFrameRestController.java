package edu.itu.wac.controller.rest;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.WebsiteCategoryService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import edu.itu.wac.util.Pa11yUtil;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class IFrameRestController {
    @GetMapping("/iframe-content")
    @ResponseBody
    String getIFrameContent(@RequestParam @NotNull String address) {

        Document doc = null;
        try {
           doc = Jsoup.connect(address).get();
        } catch (IOException e) {
            return "Website could not be read";
        }
        return doc.outerHtml();
    }
}
