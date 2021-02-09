package com.laioffer.job.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExtractRequestBody {
//data is same as monkeylearn api field, the data contains a list of strings
  public List<String> data;

  //match monekey learn api return object field
  @JsonProperty("max_keywords")
  public int maxKeywords;


  //only request body is created by ourselves, reponseItems and extractions will be processed by jackson so we dont need a constructor
  public ExtractRequestBody(List<String> data, int maxKeywords) {
    this.data = data;
    this.maxKeywords = maxKeywords;
  }
}
