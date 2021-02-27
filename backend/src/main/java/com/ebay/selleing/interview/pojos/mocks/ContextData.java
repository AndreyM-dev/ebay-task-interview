package com.ebay.selleing.interview.pojos.mocks;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContextData {
  private Boolean contextAsSite;
  private Boolean contextAsSeller;
  private Condition contextCondition;
}
