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

<title>LegendWealth</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
  <a class="navbar-brand" href="home">LegendWealth</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    <li class="nav-item">
        <a class="nav-link active" href="home">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="trade-record">Trade Record</a>
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
        <img class="d-block mx-auto mb-4" src="assets/dragon.svg" alt="" width="72" height="72">
        <h2>Welcome ${active_firstname}</h2>
        <p class="lead">Please pick any portfolio to view details.</p>
      </div>
  <div class="row">
    <div class="col-sm">
      <table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Portfolio Name</th>
      <th scope="col">Current Net Asset Value</th>
      <th scope="col">Cash in Portfolio</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  
  <c:forEach items="${portfolioList}" var="p" varStatus="status">
		<tr>
			<td>${p[0]}</td>
			<td>${p[1]}</td>
      		<td>USD ${p[2]}</td>
      		<td>
      		<div class="progress">
			  <div class="progress-bar progress-bar-striped bg-danger" role="progressbar" style="width: ${cashInPortfolioList[status.index][2]/p[2]*100}%" aria-valuenow="${cashInPortfolioList[status.index][2]/p[2]}" aria-valuemin="0" aria-valuemax="100"></div>
			</div>
      		</td>
      		<td>
      		<a class="btn btn-outline-danger" href="portfolio?id=${p[0]}" role="button">View Detail</a>
      		</td>
		</tr>
	</c:forEach>
  </tbody>
</table>
    </div>
  </div>
</div>
</body>
</html>