package edu.itu.wac.util;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.SubPageErrors;
import edu.itu.wac.entity.Website;
import edu.itu.wac.etc.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Component
public class Pa11yUtil {

    public static String pa11yPath;

    private static String fileStoragePath;

    @Value("${pa11y.path}")
    public void setPa11yPath(String pa11yPath){
        Pa11yUtil.pa11yPath = pa11yPath;
    }

    @Value("${file.storage.path}")
    public void setFileStoragePath(String fileStoragePath){
        Pa11yUtil.fileStoragePath = fileStoragePath;
    }

    public Pa11yUtil(){
        // explicit constructor for @values
    }

    @LogExecutionTime
    public static ErrorReport runPa11y(Website website, String subUrl) {
        long startTime = System.currentTimeMillis();
        ErrorReport errorReport = new ErrorReport();
        SubPageErrors subPageErrors = new SubPageErrors();
        subPageErrors.setSubPage(!StringUtils.isEmpty(subUrl) ? subUrl : website.getAddress());
        errorReport.getSubPageErrors().add(subPageErrors);
        int lineCounter = 0;
        int counter = 0;
        int errorCounter = 0;
        String errorDecs = null;
        String errorAddress = null;
        String errorScene;
        String document = null;
        ProcessBuilder builder = null;
        Process pally = null;
        if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_LINUX) {
            try {
                pally = Runtime.getRuntime().exec(pa11yPath + website.getAddress());
            } catch (IOException e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        } else if (SystemUtils.IS_OS_WINDOWS) {
            builder = new ProcessBuilder("cmd.exe", "/c",
                    "pa11y " + (!StringUtils.isEmpty(subUrl)?subUrl: website.getAddress()));
        }

        if (builder != null)
            builder.redirectErrorStream(true);
        Process p;
        try {
            BufferedReader r;
            if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_LINUX) {
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
                        subPageErrors.getErrors().add(getErrorList(website, subUrl, errorDecs,
                                errorScene, errorAddress, document));
                    }
                }
            }
        } catch (Exception e) {
           log.error(ExceptionUtils.getStackTrace(e));
        }
        errorReport.setWebsite(website);
        errorReport.setNumberOfSubPages(1);
        Document doc = null;
        try {
            doc = Jsoup.connect(!StringUtils.isEmpty(subUrl)?subUrl: website.getAddress()).get();
        } catch (IOException e) {
            log.error("Website could not be read" + subUrl);
        }

        String fileUUID = UUID.randomUUID().toString();
        subPageErrors.setHtmlPath(
                fileStoragePath +
                        fileUUID +
                        ".zip");
        if (doc!=null) {
                ZipUtil.zip(doc.outerHtml().getBytes(StandardCharsets.UTF_8),fileStoragePath,fileUUID);
        }
        log.info("Accessibility test for " + (!StringUtils.isEmpty(subUrl) ? subUrl:website.getAddress()) +
                " took " + (System.currentTimeMillis()-startTime)/1000 + " seconds.");
        return errorReport;
    }

    private static Error getErrorList(Website website, String subUrl,
                                      String errorDesc, String errorScene,
                                      String errorAddress, String document) {
        Error error = new Error();
        error.setWebsite(website);
        error.setSubPage(!StringUtils.isEmpty(subUrl) ? subUrl : website.getAddress());
        error.setErrorDesc(errorDesc);
        error.setErrorScene(errorScene);
        error.setErrorAddress(errorAddress);
        error.setDocument(document);
        return error;
    }

}
