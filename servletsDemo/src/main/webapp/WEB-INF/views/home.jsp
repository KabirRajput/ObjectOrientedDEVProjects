<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome ${active_username}</h1>

	<table>
		<tr>
			<th>Number</th>
	
		<c:forEach items="${lucky_numbers}" var="number" >
			<tr>
				<td>${number}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>