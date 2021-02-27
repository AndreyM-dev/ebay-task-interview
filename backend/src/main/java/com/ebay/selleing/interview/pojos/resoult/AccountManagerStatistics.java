package com.ebay.selleing.interview.pojos.resoult;

import com.ebay.selleing.interview.pojos.mocks.Condition;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountManagerStatistics {
  private String fullName;
  @Singular("conditionSummary")
  private Map<Condition, List<ConditionSummary>> conditionSummaryMap;
}
