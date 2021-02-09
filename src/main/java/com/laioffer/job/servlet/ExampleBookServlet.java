package com.laioffer.job.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ExampleBookServlet" /*value = "/ExampleBookServlet"*/, urlPatterns = {
    "/example_book"})
public class ExampleBookServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
//    response.setContentType("application/json");
//    ObjectMapper objectMapper = new ObjectMapper();
//    ExampleBook testBook = new ExampleBook("痛并快乐着", "白岩松", "1.1.2021",
//        "usd", "人生", "chinese",
//        "123", (float) 20.35, 500);
//    response.getWriter().print(objectMapper.writeValueAsString(testBook));


  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
//        //manually parsing Json, not efficient
//        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
//        String title = jsonRequest.getString("title");
//        String author = jsonRequest.getString("author");
//        String date = jsonRequest.getString("date");
//        float price = jsonRequest.getFloat("price");
//        String currency = jsonRequest.getString("currency");
//        int pages = jsonRequest.getInt("pages");
//        String series = jsonRequest.getString("series");
//        String language = jsonRequest.getString("language");
//        String isbn = jsonRequest.getString("isbn");
//
//        System.out.println("Title is: " + title);
//        System.out.println("Author is: " + author);
//        System.out.println("Date is: " + date);
//        System.out.println("Price is: " + price);
//        System.out.println("Currency is: " + currency);
//        System.out.println("Pages is: " + pages);
//        System.out.println("Series is: " + series);
//        System.out.println("Language is: " + language);
//        System.out.println("ISBN is: " + isbn);
//
//
//        response.setContentType("application/json");
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("status", "ok");
//        response.getWriter().print(jsonResponse);

  }
}
