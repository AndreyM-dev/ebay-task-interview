package com.ebay.selleing.interview.services;

import com.ebay.selleing.interview.pojos.mocks.Condition;
import com.ebay.selleing.interview.pojos.resoult.AccountManagerStatistics;
import com.ebay.selleing.interview.pojos.resoult.ConditionSummary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppServiceImplTest {

  private static final String DAVID = "David";
  private static final String YOSI = "Yosi";
  private static final String NONAME = "Noname";

  @Autowired
  AppService appService;

  @Test
  void getAccountManagerStatisticPositive() {
//=====================FOR NAME "David"======================================
    AccountManagerStatistics expectedForDavid = getAccountManagerStatisticsForDavid(DAVID);
    AccountManagerStatistics actualForDavid = appService.getAccountManagerStatistic(DAVID);
    assertEquals(expectedForDavid, actualForDavid);

    AccountManagerStatistics expectedForYosi = getAccountManagerStatisticsForYosi(YOSI);
    AccountManagerStatistics actualForYosi = appService.getAccountManagerStatistic(YOSI);
    assertEquals(expectedForYosi, actualForYosi);
  }

  @Test
  void getAccountManagerStatisticNegative() {
    AccountManagerStatistics expectedForNoname = getAccountManagerStatisticsForNoname(NONAME);
    AccountManagerStatistics actualForNoname = appService.getAccountManagerStatistic(NONAME);
    assertEquals(expectedForNoname, actualForNoname);
  }

  private AccountManagerStatistics getAccountManagerStatisticsForNoname(String name) {
    Map<Condition, List<ConditionSummary>> conditionSummaryMap = new HashMap<>();
    List<ConditionSummary> conditionSummariesNew = new ArrayList<>();
    // -----------Condition.NEW-----------
    conditionSummaryMap.put(Condition.NEW, conditionSummariesNew);

    List<ConditionSummary> conditionSummariesUsed = new ArrayList<>();
// -----------Condition.USED-----------
    conditionSummaryMap.put(Condition.USED, conditionSummariesUsed);

    AccountManagerStatistics expectedByNoname = AccountManagerStatistics.builder()
      .fullName(name)
      .conditionSummaryMap(conditionSummaryMap)
      .build();
    return expectedByNoname;
  }

  private AccountManagerStatistics getAccountManagerStatisticsForYosi(String name) {
    Map<Condition, List<ConditionSummary>> conditionSummaryMap = new HashMap<>();
    List<ConditionSummary> conditionSummariesNew = new ArrayList<>();
    // -----------Condition.NEW-----------
    conditionSummariesNew.add(ConditionSummary.builder()
      .epid("1134567")
      .contextAsSeller(true)
      .contextAsSite(false)
      .build());
    conditionSummaryMap.put(Condition.NEW, conditionSummariesNew);

    List<ConditionSummary> conditionSummariesUsed = new ArrayList<>();
// -----------Condition.USED-----------
    conditionSummariesUsed.add(ConditionSummary.builder()
      .epid("9934567")
      .contextAsSite(true)
      .contextAsSeller(false)
      .build()
    );

    conditionSummaryMap.put(Condition.USED, conditionSummariesUsed);

    AccountManagerStatistics expectedByYosi = AccountManagerStatistics.builder()
      .fullName(name)
      .conditionSummaryMap(conditionSummaryMap)
      .build();
    return expectedByYosi;
  }

  private AccountManagerStatistics getAccountManagerStatisticsForDavid(String name) {
    Map<Condition, List<ConditionSummary>> conditionSummaryMap = new HashMap<>();
    List<ConditionSummary> conditionSummariesNew = new ArrayList<>();
    // -----------Condition.NEW-----------
    conditionSummariesNew.add(ConditionSummary.builder()
      .epid("1134567")
      .contextAsSeller(true)
      .contextAsSite(false)
      .build());
    conditionSummaryMap.put(Condition.NEW, conditionSummariesNew);

    List<ConditionSummary> conditionSummariesUsed = new ArrayList<>();
// -----------Condition.USED-----------
    conditionSummariesUsed.add(ConditionSummary.builder()
      .epid("9923456")
      .contextAsSite(true)
      .contextAsSeller(true)
      .build());
    conditionSummariesUsed.add(ConditionSummary.builder()
      .epid("9934567")
      .contextAsSite(true)
      .contextAsSeller(false)
      .build()
    );

    conditionSummaryMap.put(Condition.USED, conditionSummariesUsed);

    AccountManagerStatistics expectedByDavid = AccountManagerStatistics.builder()
      .fullName(name)
      .conditionSummaryMap(conditionSummaryMap)
      .build();
    return expectedByDavid;
  }

}
