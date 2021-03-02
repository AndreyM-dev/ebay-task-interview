package com.ebay.selleing.interview.controlers;

import com.ebay.selleing.interview.pojos.resoult.AccountManagerStatistics;
import com.ebay.selleing.interview.services.AppService;
import com.ebay.selleing.interview.services.AppServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HelloController {
 private static final Logger log = LoggerFactory.getLogger(HelloController.class);
  @Autowired
  private AppService appService;

  @GetMapping("hello")
  public String index() {
    return "Greetings from Spring Boot!";
  }


  @GetMapping("statistic/{accountManager}")
  public AccountManagerStatistics getAccountManagerStatistic(@PathVariable String accountManager) {

    StopWatch stopWatch = new StopWatch(accountManager);
    stopWatch.start();
    AccountManagerStatistics accountManagerStatistic = appService.getAccountManagerStatistic(accountManager);
    stopWatch.stop();
    log.info("Execution Time : {}", stopWatch.getLastTaskTimeMillis());
    return accountManagerStatistic;
  }

}
