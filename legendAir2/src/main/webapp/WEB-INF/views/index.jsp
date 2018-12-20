<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Index</title>

	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="/legend-air/"><i class="fas fa-plane-departure"></i> LegendAir</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <c:if test="${ empty active_username }">
                        <li class="nav-item">
                            <a class="nav-link" href="/legend-air/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/legend-air/register">Register</a>
                        </li>
                    </c:if>
                    <c:if test="${ not empty active_username }">
                        <li class="nav-item">
                            <span class="nav-link">Welcome, ${ active_username }!</span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/legend-air/logout">Logout</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <!--        <img alt="LegendAir aircraft" src="https://images.unsplash.com/photo-1436491865332-7a61a109cc05?ixlib=rb-0.3.5&s=3fc7f6bcef909ea4fc2b4ca0469ec6a4&auto=format&fit=crop&w=1653&q=80" class="img-fluid">-->
        <form action="search" class="form-inline justify-content-center" autocomplete="off" method="GET">

            <div class="airports-field">
                <label for="origin" class="sr-only">Origin</label>
                <input type="text" class="form-control mr-4" id="origin" placeholder="Origin" name="origin">
                <div class="airports-list" id="airports-origin">
                </div>
            </div>

            <div class="airports-field" >
                <label for="destination" class="sr-only">Destination</label>
                <input type="text" class="form-control mr-4" id="destination" placeholder="Destination" name="destination">
                                <div class="airports-list" id="airports-destination">
                </div>
            </div>

            <div>
                <label for="departureDate" class="sr-only">Departure Date</label>
                <input type="text" class="form-control datepicker mr-4" id="departureDate" placeholder="Departure Date" name="departureDate">
            </div>

            <input type="submit" class="btn btn-primary" value="Search">
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js" integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E=" crossorigin="anonymous"></script>
    <script src="assets/js/index.js"></script>
</body>



</html>
