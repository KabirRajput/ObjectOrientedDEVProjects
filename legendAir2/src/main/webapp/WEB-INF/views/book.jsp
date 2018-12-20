<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Book</title>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="/legend-air/"><i
				class="fas fa-plane-departure"></i> LegendAir</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<c:if test="${ empty active_username }">
						<li class="nav-item"><a class="nav-link"
							href="/legend-air/login">Login</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/legend-air/register">Register</a></li>
					</c:if>
					<c:if test="${ not empty active_username }">
						<li class="nav-item"><span class="nav-link">Welcome,
								${ active_username }!</span></li>
						<li class="nav-item"><a class="nav-link"
							href="/legend-air/logout">Logout</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row mt-4">
			<div class="col-12">
				<h3>
					<i class="fas fa-plane-departure"></i> Flight Details
				</h3>
				<div class="row mt-4">
					<div class="col-lg-4">
						<strong>Date:</strong> ${departureDate}
					</div>
					<div class="col-lg-4">
						<strong>Time:</strong> ${departureTime} - ${arrivalTime}
					</div>
					<div class="col-lg-4">
						<strong>Flight:</strong> ${originCity} - ${destinationCity}
					</div>
				</div>
			</div>
		</div>

		<div class="row mt-4">
			<div class="col-12">
				<h3>
					<i class="fas fa-user-circle"></i> Passengers
					<button class="btn btn-primary float-right" id="passFormBtn"><i class="fas fa-plus"></i></button>
				</h3>
			</div>
		</div>

		<c:if test="${passengersData.size() > 1}">
			<c:forEach items="${passengersData}" var="passenger">
				<div class="alert alert-primary mt-4">
					<div class="row">
						<div class="col-6 text-muted">Surname/Name</div>
						<div class="col-6">
							<strong>${passenger[0]}/${passenger[1]}</strong>
						</div>
					</div>
					<div class="row">
						<div class="col-6">Passport</div>
						<div class="col-6">${passenger[4]}</div>
					</div>
				</div>
			</c:forEach>
		</c:if>

		<form method="POST" action="add-passenger" style="display: none" id="passForm">
			<div class="row">
				<div class="col-lg-6 form-group">
					<label for="firstName">First Name</label>
					<input type="text" class="form-control" id="firstName" placeholder="First name" name="name" required>
				</div>
				<div class="col-lg-6">
					<label for="lastName">Last Name</label>
					<input type="text" class="form-control" id="lastName" placeholder="Last name" name="surname" required>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 form-group">
					<label for="mobile">Mobile</label>
					<input type="text" class="form-control" id="mobile" placeholder="Mobile number" name="mobile" required>
				</div>
				<div class="col-lg-6">
					<label for="passport">Passport</label>
					<input type="text" class="form-control" id="passport" placeholder="Passport number" name="passport" required>
				</div>
			</div>
			<button type="submit" class="btn btn-success mt-sm-3 mt-lg-0" id="addPassenger">Add Passenger</button>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"
		integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E="
		crossorigin="anonymous"></script>
	<script src="assets/js/book.js">
	</script>
</body>



</html>
