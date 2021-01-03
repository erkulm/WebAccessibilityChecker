package edu.itu.wac.job;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.Website;
import edu.itu.wac.util.Pa11yUtil;

import java.util.concurrent.Callable;

public class Pa11yCallable implements Callable<ErrorReport> {
    private final String subUrl;
    private final Website website;

    public Pa11yCallable(String subUrl,Website website){
        this.subUrl = subUrl;
        this.website = website;
    }

    @Override
    public ErrorReport call() throws Exception {
        return Pa11yUtil.runPa11y(website, subUrl);
    }
}
