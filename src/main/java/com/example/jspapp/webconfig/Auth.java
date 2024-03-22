package com.example.jspapp.webconfig;

import com.example.jspapp.classes.User;
import com.example.jspapp.dao.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class Auth extends HttpServlet {

    private final UserDAOImpl userDAO = new UserDAOImpl();

    public void init() {
        System.out.println("Welcome to Library System Auth !");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(
                request, response);
        response.getWriter().println("GET method is not supported in this servlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userDAO.loginUser(email, password);
        System.out.println(user);

        if (user != null) {
            request.getSession().setAttribute("firstname", user.getFirstName());
            request.getSession().setAttribute("user", user);
            response.sendRedirect("./dashboard.jsp");
            return;
        }else{
            request.setAttribute("error", "Invalid email or password");
        }
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(
                request, response);
    }
}
