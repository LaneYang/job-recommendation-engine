package com.laioffer.job.external;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GithubClient {


  private static final String URL_TEMPLATE = "https://jobs.github.com/positions.json?description=%s&lat=%s&long=%s";
  //set default search word if the user left it empty
  private static final String DEFAULT_KEYWORD = "developer";

  public String search(double latitude, double longitude, String keyword) {
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
//create a response handler: handles response from github api and process it to our own client
    ResponseHandler<String> responseHandler = (response) -> {
      if (response.getStatusLine().getStatusCode() != 200) {
        return "";
      }

      HttpEntity httpEntity = response.getEntity();
      if(httpEntity ==null){
        return "";
      }
      return EntityUtils.toString(httpEntity);

    };
    try {
      return httpClient.execute(new HttpGet(url),responseHandler);
    }catch (Exception e){
      e.printStackTrace();
    }
    return "";
  }
}
