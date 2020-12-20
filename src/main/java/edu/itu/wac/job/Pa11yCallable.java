package edu.itu.wac.job;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.util.Pa11yUtil;

import java.util.List;
import java.util.concurrent.Callable;

public class Pa11yCallable implements Callable<Pa11yResult> {
    private final String subUrl;
    private final Website website;
    private final WebsiteCategory websiteCategory;

    public Pa11yCallable(String subUrl,Website website,WebsiteCategory websiteCategory){
        this.subUrl = subUrl;
        this.website = website;
        this.websiteCategory = websiteCategory;
    }

    @Override
    public Pa11yResult call() throws Exception {
        List<Error> errors = Pa11yUtil.runPa11y(website, subUrl, websiteCategory);
        Pa11yResult result = new Pa11yResult();
        result.setErrors(errors);
        return result;
    }
}
