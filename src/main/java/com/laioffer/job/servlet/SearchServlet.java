package com.laioffer.job.servlet;

import com.laioffer.job.external.GithubClient;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SearchServlet", /*value = "/SearchServlet"*/urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   // response.getWriter().write("test done");
    double latitude = Double.parseDouble(request.getParameter("lat"));
    double longitude = Double.parseDouble(request.getParameter("lon"));

    GithubClient githubClient = new GithubClient();
    String itemString = githubClient.search(latitude,longitude,null);
    //respond to our client
    response.setContentType("application/json");
    response.getWriter().print(itemString);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
