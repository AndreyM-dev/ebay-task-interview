package com.ebay.selleing.interview.services;

import com.ebay.selleing.interview.pojos.mocks.Condition;
import com.ebay.selleing.interview.pojos.mocks.Document;
import com.ebay.selleing.interview.pojos.mocks.Item;
import com.ebay.selleing.interview.pojos.resoult.AccountManagerStatistics;
import com.ebay.selleing.interview.pojos.resoult.ConditionSummary;
import com.ebay.selleing.interview.utils.MockReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppServiceImpl implements AppService {

  private static final Logger log = LoggerFactory.getLogger(AppServiceImpl.class);

  @Value("${totalActiveSellers}")
  private Integer totalActiveSellers;

  @Value("${totalActiveQuantity}")
  private Integer totalActiveQuantity;

  @Value("${DocsConditionNewPath}")
  private String pathDocsConditionNew;

  @Value("${pathDocsConditionUsedPath}")
  private String pathDocsConditionUsed;

  private Map<Condition, List<Document>> mockSourceData = new HashMap<>();


  @PostConstruct
  public void init() {
    try {
      mockSourceData.put(Condition.NEW, MockReader.loadListMockResponse(pathDocsConditionNew));
      mockSourceData.put(Condition.USED, MockReader.loadListMockResponse(pathDocsConditionUsed));
    } catch (Exception e) {
      e.printStackTrace();
    }
    log.info("Init success");
  }

  @Override
  @Cacheable("AccountManagerStatistics")
  public AccountManagerStatistics getAccountManagerStatistic(String accountManager) {


    Map<Condition, List<Document>> documentsByAccountManager = getDocumentsByAccountManager(accountManager);
    Map<Condition, List<ConditionSummary>> conditionalSummaryMap = getConditionalSummaryMap(documentsByAccountManager);

    return AccountManagerStatistics
      .builder()
      .fullName(accountManager)
      .conditionSummaryMap(conditionalSummaryMap)
      .build();
  }


  private Map<Condition, List<ConditionSummary>> getConditionalSummaryMap(Map<Condition, List<Document>> documentsByAccountManager) {
    Map<Condition, List<ConditionSummary>> conditionListMap = new HashMap<>();
    log.info(" Execute getConditionalSummaryMap()");
    for (Map.Entry<Condition, List<Document>> entries : documentsByAccountManager.entrySet()) {
      List<ConditionSummary> conditionSummaries = entries.getValue().stream()
        .filter(v -> v.getProduct().getTotalActiveSellers() > totalActiveSellers
          || v.getProduct().getTotalActiveQuantity() > totalActiveQuantity)
        .map(v ->
          ConditionSummary.builder()
            .epid(v.getProduct().getEpid())
            .contextAsSeller(v.getProduct().getContextData().getContextAsSeller())
            .contextAsSite(v.getProduct().getContextData().getContextAsSite())
            .build())
        .collect(Collectors.toList());
      conditionListMap.put(entries.getKey(), conditionSummaries);
    }
    return conditionListMap;
  }

  private Map<Condition, List<Document>> getDocumentsByAccountManager(String accountManager) {
    Map<Condition, List<Document>> conditionListMap = new HashMap<>();
    log.info(" Execute getDocumentsByAccountManager() ");

    for (Map.Entry<Condition, List<Document>> entries : mockSourceData.entrySet()) {
      List<Document> collect = entries.getValue().stream()
        .filter(v -> {
          Optional<Item> item = v.getItems().stream()
            .filter(j -> j.getAccountManager() != null
              && j.getAccountManager().strip().equals(accountManager))
            .findFirst();
          return item.isPresent();
        })
        .collect(Collectors.toList());
      conditionListMap.put(entries.getKey(), collect);
    }
    return conditionListMap;
  }
}
