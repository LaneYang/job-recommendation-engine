package com.laioffer.job.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ExampleBookServlet" /*value = "/ExampleBookServlet"*/, urlPatterns = {"/example_book"})
public class ExampleBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("This is the example book servlet");
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        System.out.println("Keyword is:"+ keyword);
        System.out.println("Category is"+category);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
