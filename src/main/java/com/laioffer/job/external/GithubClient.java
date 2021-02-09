package com.laioffer.job.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.job.entity.Item;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GithubClient {


  private static final String URL_TEMPLATE = "https://jobs.github.com/positions.json?description=%s&lat=%s&long=%s";
  //set default search word if the user left it empty
  private static final String DEFAULT_KEYWORD = "developer";

  public List<Item> search(double latitude, double longitude, String keyword) {
    if (keyword == null) {
      keyword = DEFAULT_KEYWORD;
    }
    //process chars like spaces
    try {
      keyword = URLEncoder.encode(keyword, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    //format the url using our template above
    String url = String.format(URL_TEMPLATE, keyword, latitude, longitude);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    ResponseHandler<List<Item>> responseHandler = new GithubResponseHandler();
    try {
      return httpClient.execute(new HttpGet(url), responseHandler);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  private static void extractKeywords(List<Item> items) {
    MonkeyLearnClient monkeyLearnClient = new MonkeyLearnClient();
    List<String> descriptions = new ArrayList<>();
    for (Item item : items) {
      String description = item.getDescription().replace("·", " ");
      descriptions.add(description);

    }

    List<Set<String>> keywordList = monkeyLearnClient.extract(descriptions);
    for (int i = 0; i < items.size(); i++) {
      items.get(i).setKeywords(keywordList.get(i));
    }
  }

  private static class GithubResponseHandler implements ResponseHandler<List<Item>> {

    @Override
    public List<Item> handleResponse(HttpResponse httpResponse)
        throws ClientProtocolException, IOException {

      //create a response handler: handles response from github api and process it to our own client
      if (httpResponse.getStatusLine().getStatusCode() != 200) {
        return Collections.emptyList();
      }

      HttpEntity httpEntity = httpResponse.getEntity();
      if (httpEntity == null) {
        return Collections.emptyList();
      }
      ObjectMapper objectMapper = new ObjectMapper();
//      return Arrays.asList(objectMapper.readValue(httpEntity.getContent(), Item[].class
//      ));
      List<Item> items = Arrays
          .asList(objectMapper.readValue(httpEntity.getContent(), Item[].class));
      extractKeywords(items);
      return items;


    }
  }
}

