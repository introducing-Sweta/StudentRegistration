<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join Us</title>
<link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei"
	rel="stylesheet">
<link href="./css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div class="box">
			<img class="avatar" src="img/students.jpg">
			<h1>Login Account</h1>
			<form action="<%=request.getContextPath()%>/welcome" method="post">
				<p>Useremail</p>
				<input type="text" placeholder="Useremail" name="email" required>
				<p>Password</p>
				<input type="password" placeholder="Password" name="password"
					required> <input type="submit" value="Login"> <a
					href="#">Forget Password?</a><br> <a href="registration.jsp">Create
					New Account</a>
			</form>
		</div>
	</div>
</body>
</html>

