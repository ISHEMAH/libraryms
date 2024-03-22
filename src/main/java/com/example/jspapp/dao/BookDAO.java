package com.example.jspapp.dao;

import com.example.jspapp.classes.Book;

import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    Book getBook(int id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(int id);
    List<Book> searchBooks(String searchQuery);
}
