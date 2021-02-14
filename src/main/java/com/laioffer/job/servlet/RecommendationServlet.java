package com.laioffer.job.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.job.entity.Item;
import com.laioffer.job.entity.ResultResponse;
import com.laioffer.job.recommendation.Recommendation;
import java.util.List;
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
    response.setContentType("application/json");
    ObjectMapper mapper = new ObjectMapper();
    HttpSession session = request.getSession(false);
    if (session == null) {
      response.setStatus(403);
      mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
      return;
    }
    String userId = request.getParameter("user_id");
    double lat = Double.parseDouble(request.getParameter("lat"));
    double lon = Double.parseDouble(request.getParameter("lon"));

    Recommendation recommendation = new Recommendation();
    List<Item> items = recommendation.recommendItems(userId, lat, lon);
    mapper.writeValue(response.getWriter(), items);


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
