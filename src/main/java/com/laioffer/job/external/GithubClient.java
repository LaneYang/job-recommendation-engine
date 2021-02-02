package com.laioffer.job.external;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GithubClient {

  private static final String URL_TEMPLATE = "https://jobs.github.com/positions.json?description=%s&lat=%s&long=%s";
  private static final String DEFAULT_KEYWORD = "developer";

  public String search(double latitude, double longitude, String keyword){
    if (keyword == null){
      keyword = DEFAULT_KEYWORD;
    }
    //process chars like spaces
    try {
      keyword= URLEncoder.encode(keyword,"UTF-8" );
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    String url = String.format(URL_TEMPLATE,keyword,latitude,longitude);
    CloseableHttpClient httpClient = HttpClients.createDefault();

  }
}
