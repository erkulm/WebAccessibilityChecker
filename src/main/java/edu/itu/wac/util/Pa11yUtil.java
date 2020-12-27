package edu.itu.wac.util;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.etc.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class Pa11yUtil {

    @Value("${pa11y.path}")
    private static String pa11yPath;

    @LogExecutionTime
    public static List<Error> runPa11y(Website website, String subUrl) {
        List<Error> error = new ArrayList<>();
        int lineCounter = 0;
        int counter = 0;
        int errorCounter = 0;
        String errorDecs = null;
        String errorAddress = null;
        String errorScene;
        String document = null;
        ProcessBuilder builder = null;
        String reportId = UUID.randomUUID().toString();
        Process pally = null;
        if (SystemUtils.IS_OS_MAC) {
            String[] arguments = new String[]{website.getAddress()};
            try {
                pally = Runtime.getRuntime().exec("/usr/local/lib/node_modules/pa11y/bin/pa11y.js " + website.getAddress());
            } catch (IOException e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
//            builder = new ProcessBuilder("pa/1y", website.getAddress());
//            builder = new ProcessBuilder("osascript", "-e",
//                    "tell application \"Terminal\" to do script \"pa11y "+website.getSubUrl()+"\"");
        } else if (SystemUtils.IS_OS_WINDOWS) {
            builder = new ProcessBuilder("cmd.exe", "/c",
                    pa11yPath + subUrl);
        }
        if (builder != null)
            builder.redirectErrorStream(true);
        Process p;
        try {
            BufferedReader r;
            if (SystemUtils.IS_OS_MAC) {
                r = new BufferedReader(new InputStreamReader(pally.getInputStream(), "UTF8"), 8);
            } else {
                p = builder.start();

                r = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF8"), 8);
            }
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
                        errorAddress = errorAddress.replace("\"", "");
                        errorAddress = errorAddress.replace("\'", "");
                        counter = 3;
                    } else if (counter == 3) {
                        errorScene = line.substring(7);
                        counter = 0;
                        error.add(getErrorList(website, subUrl, errorDecs,
                                errorScene, errorAddress, document,
                                reportId));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error;
    }

    private static Error getErrorList(Website website, String subUrl,
                                      String errorDesc, String errorScene,
                                      String errorAddress, String document,
                                      String reportId) {
        Error error = new Error();
        error.setWebsite(website);
        error.setSubPage(subUrl);
        error.setErrorDesc(errorDesc);
        error.setErrorScene(errorScene);
        error.setErrorAddress(errorAddress);
        error.setDocument(document);
        error.setReportId(reportId);
        return error;
    }

}
