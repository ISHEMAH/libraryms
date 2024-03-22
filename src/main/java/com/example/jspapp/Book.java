package com.example.jspapp;


import java.util.Date;

public class Book {
    private int id;
    private String name;
    private String author;
    private String publisher;
    private Date publicationDate;
    private String subject;

    // Constructor
    public Book(int id, String name, String author, String publisher, Date publicationDate, String subject) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.subject = subject;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
