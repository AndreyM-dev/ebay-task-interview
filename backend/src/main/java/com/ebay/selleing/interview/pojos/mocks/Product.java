package com.ebay.selleing.interview.pojos.mocks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  private String epid;
  private int totalActiveQuantity;
  private int totalActiveSellers;
  private ContextData contextData;
}
