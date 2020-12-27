package edu.itu.wac.util;

import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.etc.LogExecutionTime;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SubUrlUtil {

    @LogExecutionTime
    public static List<String> getSubUrls(Website website) {
        List<String> subUrlList = new ArrayList<>();
        try {
            String url = website.getAddress();
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("a");
            elements.forEach(element -> addSubUrls(element, subUrlList, website.getAddress()));
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return subUrlList;
    }

    private static void addSubUrls(Element element, List<String> subUrlList, String url){
        String subUrl = element.absUrl("href");
        try {
            if (subUrl.length() > url.length() && subUrl.startsWith(url) && !subUrlList.contains(subUrl)) {
                subUrlList.add(subUrl);
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
