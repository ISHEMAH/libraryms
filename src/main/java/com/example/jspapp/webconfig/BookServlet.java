package com.example.jspapp.webconfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.jspapp.classes.Book;
import com.example.jspapp.dao.BookDAO;
import com.example.jspapp.dao.impl.BookDAOImpl;

@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {

    private BookDAO bookDAO;

    public void init() throws ServletException {
        super.init();
        bookDAO = new BookDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String publicationDateStr = request.getParameter("publicationDate");
        String subject = request.getParameter("subject");
        Date publicationDate = null;
        try {
            publicationDate = new SimpleDateFormat("yyyy-MM-dd").parse(publicationDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Book newBook = new Book(1, name, author, publisher, publicationDate, subject);
        bookDAO.addBook(newBook);
        response.sendRedirect("addBook.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("firstname") == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        String action = request.getParameter("action");

        if (action != null && action.equals("display")) {
            List<Book> books = bookDAO.getAllBooks();

            request.setAttribute("books", books);

            request.getRequestDispatcher("WEB-INF/displayBook.jsp").forward(request, response);
        } else {
            // Handle other actions or requests
        }
    }
}
