package com.ebay.selleing.interview.utils;

import com.ebay.selleing.interview.pojos.mocks.Document;
import com.ebay.selleing.interview.pojos.mocks.MockHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MockReader {

  private ObjectMapper jsonMapper = new ObjectMapper();

  /**
   * Get path to resource file with Documents list and return it as list of Documents objects
   *
   * @param mockFilePath path to resource file
   * @return List<Document>
   * @throws Exception un eny read problem
   */
  public List<Document> loadListMockResponse(String mockFilePath) throws Exception {
    MockHolder mockHolder;
    mockHolder = jsonMapper.readValue(ClassLoader.getSystemClassLoader().getResourceAsStream(mockFilePath), MockHolder.class);
    return mockHolder.getDocList();
  }
}
