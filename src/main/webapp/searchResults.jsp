<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/22/2024
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h2>Search Results</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Author</th>
        <th>Publisher</th>
        <th>Publication Date</th>
        <th>Subject</th>
    </tr>
    <%-- Iterate through the list of books and display each book information --%>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.publisher}</td>
            <td>${book.publicationDate}</td>
            <td>${book.subject}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
