package com.example.jspapp;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.io.IOException;
import java.sql.Connection; // Import the Connection class
import java.sql.DriverManager; // Import the DriverManager class
import java.sql.PreparedStatement; // Import the PreparedStatement class
import java.sql.ResultSet; // Import the ResultSet class
import java.sql.SQLException; // Import the SQLException class
import java.util.ArrayList; // Import the ArrayList class
import java.util.List; // Import the List interface


@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Extract the search query parameter
            String searchQuery = request.getParameter("searchQuery");

            // JDBC connection setup (assuming you have already set up your database connection)
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "user", "")) {
                // Create a PreparedStatement to query books matching the search query
                String sql = "SELECT * FROM Book WHERE name LIKE ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Set the search query parameter for the PreparedStatement
                    statement.setString(1, "%" + searchQuery + "%");

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) {
                        List<Book> books = new ArrayList<>();
                        // Iterate through the ResultSet to retrieve book information
                        while (resultSet.next()) {
                            int id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String author = resultSet.getString("author");
                            String publisher = resultSet.getString("publisher");
                            Date publicationDate = resultSet.getDate("publication_date");
                            String subject = resultSet.getString("subject");

                            // Create a Book object and add it to the list
                            Book book = new Book(id, name, author, publisher, publicationDate, subject);
                            books.add(book);
                        }

                        // Set the list of books as an attribute in the request
                        request.setAttribute("books", books);

                        // Forward the request to a JSP page to display the search results
                        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database errors
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action parameter from the request
        String action = request.getParameter("action");

        // Check the action parameter to determine the appropriate action
        if ("showLogin".equals(action)) {
            // Forward to the login page
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if ("showRegister".equals(action)) {
            // Forward to the registration page
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // Forward to a default page or display an error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

}
