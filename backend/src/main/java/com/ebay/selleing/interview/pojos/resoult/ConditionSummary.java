package com.ebay.selleing.interview.pojos.resoult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionSummary {
  private String epid;
  private Boolean contextAsSite;
  private Boolean contextAsSeller;
}
