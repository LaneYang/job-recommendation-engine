package com.laioffer.job.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.job.entity.ResultResponse;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecommendationServlet", urlPatterns = {
    "/recommendation"}/*value = "/RecommendationServlet"*/)
public class RecommendationServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().write("test done");
    ObjectMapper mapper = new ObjectMapper();
    HttpSession session = request.getSession(false);
    if (session == null) {
      response.setStatus(403);
      mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
      return;
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    HttpSession session = request.getSession(false);
    if (session == null) {
      response.setStatus(403);
      mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
      return;
    }


  }
}
