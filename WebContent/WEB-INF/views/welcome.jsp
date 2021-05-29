<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
       <h2>
 Welcome, <%= request.getAttribute("firstname") %></h2>

<h2>Your Account ID: <%= request.getAttribute("studentId") %> </h2>
<h2>
Your Email: <%= request.getAttribute("email") %> </h2>
<h2>
Your Contact: <%= request.getAttribute("contact") %></h2> 
</body>
</html>