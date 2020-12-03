package edu.itu.wac.util;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Pa11yUtil {

    @Value("${pa11y.path}")
    private static String pa11yPath;

    public static List<Error> runPa11y(Website website, String subUrl, WebsiteCategory category) {
        List<Error> error = new ArrayList<>();
        int lineCounter = 0;
        int counter = 0;
        int errorCounter = 0;
        String errorDecs = null;
        String errorAddress = null;
        String errorScene;
        String document = null;
        ProcessBuilder builder = null;
        if (SystemUtils.IS_OS_MAC) {
            builder = new ProcessBuilder("pa11y", website.getAddress());
        }else if (SystemUtils.IS_OS_WINDOWS){
            builder = new ProcessBuilder("cmd.exe", "/c",
                    pa11yPath + subUrl);
        }
        builder.redirectErrorStream(true);
        Process p;
        try {
            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF8"), 8);
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                lineCounter++;
                if (lineCounter > 6 && !line.equals("")) {
                    if (line.indexOf("Error") > 0 && counter == 0 && line.length() > 10) {
                        errorCounter += 1;
                        counter = 1;
                        errorDecs = line.substring(10);
                    } else if (counter == 1) {
                        document = line.substring(7);
                        counter = 2;
                    } else if (counter == 2) {
                        errorAddress = line.substring(7);
                        counter = 3;
                    } else if (counter == 3) {
                        errorScene = line.substring(7);
                        counter = 0;
                        error.add(getErrorList(website, subUrl, errorDecs,
                                errorScene, errorAddress, document,
                                category));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error;
    }

    private static Error getErrorList(Website website, String subUrl, String errorDesc, String errorScene, String errorAddress, String document, WebsiteCategory category) {
        Error error = new Error();
        error.setWebsite(website);
        error.setSubPage(subUrl);
        error.setErrorDesc(errorDesc);
        error.setErrorScene(errorScene);
        error.setErrorAddress(errorAddress);
        error.setDocument(document);
        error.setCategory(category);
        return error;
    }

}
