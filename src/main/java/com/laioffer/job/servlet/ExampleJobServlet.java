package com.laioffer.job.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ExampleJobServlet"/*, value = "/ExampleJobServlet"*/, urlPatterns = {
    "/example_job"})
public class ExampleJobServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
//    response.setContentType("application/json");
//    ObjectMapper mapper = new ObjectMapper();
//    ExampleCoordinates coordinates = new ExampleCoordinates(37.485130, -122.148316);
//    ExampleJob job = new ExampleJob("SDE", 100000, "jan 29, 2021", true, coordinates);
//    response.getWriter().print(mapper.writeValueAsString(job));
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
