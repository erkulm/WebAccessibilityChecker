package edu.itu.wac.job;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.Website;
import edu.itu.wac.repository.WebsiteRepository;
import edu.itu.wac.util.SubUrlUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@Slf4j
@Component
public class Pa11yExecutor implements Job {
  @Setter @Getter private Website website;

  @Value("${number.of.threads.pa11y}")
  Integer numberOfThreads;

  @Autowired WebsiteRepository websiteRepository;

  public ErrorReport executePally(Website website) {
    this.website = website;

    List<String> subUrls = SubUrlUtil.getSubUrls(website);

    ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

    List<FutureTask<ErrorReport>> futureTasks = new ArrayList<>();
    subUrls.forEach(subUrl -> addNewFutureTask(futureTasks, subUrl));
    futureTasks.forEach(executor::execute);

    waitIfNotAllTasksAreDone(futureTasks);
    ErrorReport errorReport = new ErrorReport();
    errorReport.setNumberOfSubPages(subUrls.size());
    futureTasks.forEach(futureTask -> getPa11lResult(errorReport, futureTask));

    executor.shutdown();

    return errorReport;
  }

  private void getPa11lResult(ErrorReport errorReport, FutureTask<ErrorReport> task) {
    ErrorReport partOfErrorReport = null;
    try {
      partOfErrorReport = task.get();
    } catch (InterruptedException | ExecutionException e) {
      log.error(ExceptionUtils.getStackTrace(e));
    }
    if (partOfErrorReport != null) {
      errorReport.getSubPageErrors().addAll(partOfErrorReport.getSubPageErrors());
    }
  }

  private void addNewFutureTask(List<FutureTask<ErrorReport>> futureTasks, String subUrl) {
    Pa11yCallable callable = new Pa11yCallable(subUrl, website);
    FutureTask<ErrorReport> futureTask = new FutureTask<>(callable);
    futureTasks.add(futureTask);
  }

  private void waitIfNotAllTasksAreDone(List<FutureTask<ErrorReport>> futureTasks) {
    boolean done = false;
    while (!done) {
      done = futureTasks.stream().allMatch(FutureTask::isDone);
      if (!done) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          log.error(ExceptionUtils.getStackTrace(e));
        }
      }
    }
  }

  @SneakyThrows
  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    log.info("Pa11y started");
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    numberOfThreads = 1;
    List<Website> websites = websiteRepository.findAll();
    log.info("Pa11y will run on " + websites.size() + "websites");
    for (Website scheduledWebsite : websites) {
      log.info("pa11y running on: " + scheduledWebsite.getAddress());
      // executePally(scheduledWebsite);
    }
  }
}
