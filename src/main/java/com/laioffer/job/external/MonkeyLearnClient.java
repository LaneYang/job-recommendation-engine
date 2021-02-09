package com.laioffer.job.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.job.entity.ExtractRequestBody;
import com.laioffer.job.entity.ExtractResponseItem;
import com.laioffer.job.entity.Extraction;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class MonkeyLearnClient {

  private static final String EXTRACT_URL = "https://api.monkeylearn.com/v3/extractors/ex_YCya9nrn/extract/";
  private static final String AUTH_TOKEN = "73c9936f13c9c2430a3e05d30bc0b860418e9c90";


  public List<Set<String>> extract(List<String> articles) {
    //Jackson guide: http://tutorials.jenkov.com/java-json/jackson-objectmapper.html
    ObjectMapper objectMapper = new ObjectMapper();
    //apache http guide: https://www.tutorialspoint.com/apache_httpclient/apache_httpclient_response_handlers.html
    //https://hc.apache.org/httpcomponents-client-4.3.x/tutorial/html/index.html

    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(EXTRACT_URL);
    httpPost.setHeader("Content-type", "application/json");
    httpPost.setHeader("Authorization", "Token " + AUTH_TOKEN);
    ExtractRequestBody body = new ExtractRequestBody(articles, 3);
    String jsonBody;
    try {
      jsonBody = objectMapper.writeValueAsString(body);
    } catch (JsonProcessingException e) {
      return Collections.emptyList();
    }

    try {
      httpPost.setEntity(new StringEntity(jsonBody));
    } catch (UnsupportedEncodingException e) {
      return Collections.emptyList();
    }

    ResponseHandler<List<Set<String>>> responseHandler = response -> {
      if (response.getStatusLine().getStatusCode() != 200) {
        return Collections.emptyList();
      }
      HttpEntity entity = response.getEntity();
      if (entity == null) {
        return Collections.emptyList();
      }

      ExtractResponseItem[] results = objectMapper
          .readValue(entity.getContent(), ExtractResponseItem[].class);
      List<Set<String>> keywordList = new ArrayList<>();
      for (ExtractResponseItem result : results) {
        Set<String> keywords = new HashSet<>();
        for (Extraction extraction : result.extractions) {
          keywords.add(extraction.parsedValue);
        }
        keywordList.add(keywords);
      }
      return keywordList;
    };

    try {
      return closeableHttpClient.execute(httpPost, responseHandler);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();


  }
  public static void main(String[] args) {

    List<String> articles = Arrays.asList(
        "Elon Musk has shared a photo of the spacesuit designed by SpaceX. This is the second image shared of the new design and the first to feature the spacesuit’s full-body look.",
        "Former Auburn University football coach Tommy Tuberville defeated ex-US Attorney General Jeff Sessions in Tuesday nights runoff for the Republican nomination for the U.S. Senate. ",
        "The NEOWISE comet has been delighting skygazers around the world this month – with photographers turning their lenses upward and capturing it above landmarks across the Northern Hemisphere."
    );

    MonkeyLearnClient client = new MonkeyLearnClient();

    List<Set<String>> keywordList = client.extract(articles);
    System.out.println(keywordList);
  }

}