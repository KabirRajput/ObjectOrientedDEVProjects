<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<title>LegendWealth</title>
</head>
<body>
<%-- ${portfolio} --%>
<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
  <a class="navbar-brand" href="home">LegendWealth</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    <li class="nav-item">
        <a class="nav-link" href="home">Home</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="trade-record">Trade Record <span class="sr-only">(current)</span></a>
      </li>
    </ul>
    <span class="navbar-text text-light">
            ${active_broker.firstName} ${active_broker.lastName} | 
            </span>
            <ul class="navbar-nav">
    <li class="nav-item">
        <a class="nav-link active" href="logout">Logout</a>
      </li>
    </ul>
  </div>
</nav>


<div class="container">
<div class="py-5">
            <h2>${portfolio.name}</h2>
<!--             <p class="lead">Current Net Asset Value: USD <span id="portfolioSumTotal"></span></p> -->
      <label for="myInput">Filter asset by typing the asset name, asset code, etc</label>
      <input class="form-control" id="myInput" type="text" placeholder="Search">
</div>
  <div class="row">
    <div class="col-sm">
      <table class="table table-hover table-sm">
  <thead>
    <tr>
      <th scope="col">Code</th>
      <th scope="col">Type</th>
      <th scope="col">Price</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody id="myTable">
  <c:forEach items="${assetList}" var="a">
  	  <c:if test = "${a.assetType.description != 'Cash'}">
		<tr>
			<td>${a.code}</td>
			<td>${a.assetType.description}</td>
			<td>$${a.price}</td>
			<td><a class="btn btn-outline-danger btn-sm" href="trade-detail?portfolio_id=${portfolio.portfolioId}&asset_id=${a.assetId}" role="button">Choose</a></td>
		</tr>
	 </c:if>
	</c:forEach>
  </tbody>
</table>
    </div>
  </div>
</div>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});


</script>
</body>
</html>