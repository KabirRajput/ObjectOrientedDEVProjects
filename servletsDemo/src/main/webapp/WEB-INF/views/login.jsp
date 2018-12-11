<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<title>Insert title here</title>

</head>
<body>
	<div class = " form-container>">
		<form method = "POST" action = "login">
		<p>First name:</p>
  		<input type="text" name="form_firstname" placeholder="Mickey" required>
  		<br>
  		<p>Password:</p>
  		<input type="password" name="form_password" placeholder="Enter Password" required>
  		<br><br>
  
 	 	<input type="submit" value="SUBmit">
		</form>
	</div>

</body>
</html>