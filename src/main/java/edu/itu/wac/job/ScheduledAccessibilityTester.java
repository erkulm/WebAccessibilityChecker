package edu.itu.wac.job;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.Website;
import edu.itu.wac.repository.WebsiteRepository;
import edu.itu.wac.service.ErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
      Pa11yExecutor pa11yExecutor, WebsiteRepository websiteRepository,ErrorService errorService) {
    this.pa11yExecutor = pa11yExecutor;
    this.websiteRepository = websiteRepository;
    this.errorService = errorService;
  }

  @Scheduled(cron = "0 37 17 * * ?")
  @Async
  public void startJobAfterApplicationReady() {
    long start = System.currentTimeMillis();
    log.info("Scheduled accessibility tests have started.");
    Optional<Website> website = websiteRepository.findByAddress("https://www.vodafone.com.tr/");
    if (website.isPresent()) {
      pa11yExecutor.numberOfThreads = 5;
      ErrorReport errorReport = pa11yExecutor.executePally(website.get());
      errorService.saveErrorReport(website.get(),start,errorReport);
    }
    //    ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
    //
    //    Pa11yExecutor executor = new Pa11yExecutor();
    //
    //    ses.scheduleAtFixedRate(executor, 0, 60, TimeUnit.SECONDS);
    log.info(
        "Scheduled accessibility tests have been completed in "
            + (System.currentTimeMillis() - start) / 1000
            + " seconds.");
  }
}
