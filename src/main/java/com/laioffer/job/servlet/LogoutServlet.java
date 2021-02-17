package com.laioffer.job.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.job.entity.ResultResponse;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //false: get current session, if there is no session, will not create one
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();//delete session from server memory
    }

    ObjectMapper mapper = new ObjectMapper();
    response.setContentType("application/json");
    ResultResponse resultResponse = new ResultResponse("OK");
    mapper.writeValue(response.getWriter(), resultResponse);

  }
}
