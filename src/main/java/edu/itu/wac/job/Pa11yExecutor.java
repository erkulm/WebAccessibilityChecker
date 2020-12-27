package edu.itu.wac.job;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.util.SubUrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@Slf4j
@Component
public class Pa11yExecutor {
    private Website website;

    @Value("${number.of.threads.pa11y}")
    Integer numberOfThreads;

    public List<Error> executePally(Website website){
        this.website = website;

        List<String> subUrls = SubUrlUtil.getSubUrls(website);

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        List<FutureTask<Pa11yResult>> futureTasks = new ArrayList<>();
        subUrls.forEach(subUrl->addNewFutureTask(futureTasks,subUrl));
        futureTasks.forEach(executor::execute);

//        waitIfNotAllTasksAreDone(futureTasks);

        List<Error> results = new ArrayList<>();
        futureTasks.forEach(futureTask->getPa11lResult(results, futureTask));

        return results;
    }

    private void getPa11lResult(List<Error> results, FutureTask<Pa11yResult> task) {
        Pa11yResult result = new Pa11yResult();
            try {
                result = task.get();
            } catch (InterruptedException | ExecutionException e) {
               log.error(ExceptionUtils.getStackTrace(e));
            }
        results.addAll(result.getErrors());
    }

    private void addNewFutureTask(List<FutureTask<Pa11yResult>> futureTasks, String subUrl) {
        Pa11yCallable callable = new Pa11yCallable(subUrl, website);
        FutureTask<Pa11yResult> futureTask = new FutureTask<>(callable);
        futureTasks.add(futureTask);
    }

    private void waitIfNotAllTasksAreDone(List<FutureTask<Pa11yResult>> futureTasks) {
        boolean done = false;
        while (!done){
            done = futureTasks.stream().allMatch(FutureTask::isDone);
            if (!done){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }
    }
}
