package com.example.jspapp.dao.impl;

import com.example.jspapp.classes.Book;
import com.example.jspapp.config.DatabaseConfig;
import com.example.jspapp.dao.BookDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private Connection connection;
    public BookDAOImpl() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addBook(Book book) {
        String query = "INSERT INTO books (name, author,publisher,publicationDate, subject) VALUES (?, ?, ?,?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setDate(4, (Date) book.getPublicationDate());
            statement.setString(5, book.getSubject());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Book getBook(int id) {
        String query = "SELECT * FROM books WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Book(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getDate("publicationDate"),
                        resultSet.getString("subject"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getDate("publicationDate"),
                        resultSet.getString("subject"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateBook(Book book) {
        String query = "UPDATE books SET name=?, author=?, publisher=?, publicationDate=?, subject=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setDate(4, new Date(book.getPublicationDate().getTime())); // Convert util.Date to sql.Date
            statement.setString(5, book.getSubject());
            statement.setInt(6, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        String query = "DELETE FROM books WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> searchBooks(String searchQuery) {
        List<Book> bookList = new ArrayList<>();
        try {
            String query = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchQuery + "%");
            statement.setString(2, "%" + searchQuery + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getDate("publishedDate"),
                        resultSet.getString("subject")
                );
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
