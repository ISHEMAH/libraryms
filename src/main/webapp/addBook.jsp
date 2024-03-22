<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<h1>Add New Book</h1>
<form action="bookServlet" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="author">Author:</label>
    <input type="text" id="author" name="author" required><br><br>

    <label for="publisher">Publisher:</label>
    <input type="text" id="publisher" name="publisher" required><br><br>

    <label for="publicationDate">Publication Date (YYYY-MM-DD):</label>
    <input type="text" id="publicationDate" name="publicationDate" required><br><br>

    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject" required><br><br>

    <input type="submit" value="Add Book">
</form>
</body>
</html>
