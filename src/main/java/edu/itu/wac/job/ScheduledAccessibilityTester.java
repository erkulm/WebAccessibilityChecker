package edu.itu.wac.job;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.Website;
import edu.itu.wac.repository.WebsiteRepository;
import edu.itu.wac.service.ErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@EnableAsync
@EnableScheduling
public class ScheduledAccessibilityTester {
  private final Pa11yExecutor pa11yExecutor;
  private final WebsiteRepository websiteRepository;
  private final ErrorService errorService;

  @Autowired
  public ScheduledAccessibilityTester(
      Pa11yExecutor pa11yExecutor, WebsiteRepository websiteRepository, ErrorService errorService) {
    this.pa11yExecutor = pa11yExecutor;
    this.websiteRepository = websiteRepository;
    this.errorService = errorService;
  }

  @Value("${accessibility.job.websites}")
  private String[] websites;

  @Scheduled(cron = "${accessibility.job.cron}")
  @Async
  public void startJobAfterApplicationReady() {
    long start = System.currentTimeMillis();
    log.info("Scheduled accessibility tests have started.");
    List<Website> websites = websiteRepository.findByAddressIn(Arrays.asList(this.websites));
    if (!websites.isEmpty()) {
      pa11yExecutor.numberOfThreads = 5;
      for (Website website : websites) {
        ErrorReport errorReport = pa11yExecutor.executePally(website);
        errorService.saveErrorReport(website, start, errorReport);
      }
    }
    log.info(
        "Scheduled accessibility tests have been completed in "
            + (System.currentTimeMillis() - start) / 1000
            + " seconds.");
  }
}
