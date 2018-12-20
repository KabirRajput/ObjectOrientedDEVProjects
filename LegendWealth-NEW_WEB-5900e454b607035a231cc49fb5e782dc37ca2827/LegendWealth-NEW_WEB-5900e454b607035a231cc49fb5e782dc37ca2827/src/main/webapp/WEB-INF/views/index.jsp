<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<style>
html, body {
	height: 100%;
}

body {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	width: 100%;
	max-width: 400px;
	padding: 15px;
	margin: auto;
}

.form-signin .checkbox {
	font-weight: 400;
}

.form-signin .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>
<title>LegendWealth</title>
</head>
<body class="text-center">


	<form class="form-signin" method="POST" action="login">
		<div class="form-group">
			<img class="mb-4" src="assets/dragon.svg" alt="" width="72"
				height="72">
			<h1 class="h3 mb-3 font-weight-normal">Welcome to Legend Wealth</h1>
			
			<input type="text" class="form-control" id="exampleInputEmail1"
				aria-describedby="emailHelp" placeholder="Enter Username"
				name="login_form_username" required>
		</div>
		<div class="form-group">
			<input type="password" class="form-control"
				id="exampleInputPassword1" placeholder="Enter Password"
				name="login_form_password" required> 
				<c:if test="${logout_success == true}">
				<div class="alert alert-success" role="alert">
				Logout successful, wish you every success!
				</div>
				</c:if>
				<c:choose>
				    <c:when test="${login_fail == true}">
				    <div class="alert alert-danger" role="alert">
				        Your username or password was incorrect.
				        </div>
				    </c:when>    
				    <c:otherwise>
				<small id="emailHelp"
				class="form-text text-muted">
				        Please request administrator for creating account or changing password.
				</small>
				    </c:otherwise>
				</c:choose>
				
		</div>
		<button type="submit" class="btn btn-danger">Login</button>
	</form>


</body>
</html>