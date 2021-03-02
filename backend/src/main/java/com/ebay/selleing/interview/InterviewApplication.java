package com.ebay.selleing.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class InterviewApplication {

  public static void main(String[] args) {
    SpringApplication.run(InterviewApplication.class, args);
  }

}
