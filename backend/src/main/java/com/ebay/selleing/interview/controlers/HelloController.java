package com.ebay.selleing.interview.controlers;

import com.ebay.selleing.interview.pojos.resoult.AccountManagerStatistics;
import com.ebay.selleing.interview.services.AppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

  @Autowired
  private AppServiceImpl appService;

  @GetMapping("hello")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @GetMapping("statistic/{accountManager}")
  public AccountManagerStatistics getAccountManagerStatistic(@PathVariable String accountManager) {
    return appService.getAccountManagerStatistic(accountManager);
  }

}
