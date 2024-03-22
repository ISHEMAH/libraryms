package com.example.jspapp.webconfig;

import com.example.jspapp.classes.Book;
import com.example.jspapp.dao.BookDAO;
import com.example.jspapp.dao.impl.BookDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search-book")
public class BookSearchServlet extends HttpServlet {
    private BookDAO bookDAO;

    public void init() throws ServletException {
        super.init();
        bookDAO = new BookDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");

        // Perform the search using the BookDAO
        List<Book> searchResults = bookDAO.searchBooks(searchQuery);

        // Store the search results in request attributes to pass to JSP
        request.setAttribute("searchResults", searchResults);

        // Forward the request to a JSP page to display the search results
        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
