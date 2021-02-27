package com.ebay.selleing.interview.pojos.mocks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
  private Product product;
  private List<Item> items;
}
