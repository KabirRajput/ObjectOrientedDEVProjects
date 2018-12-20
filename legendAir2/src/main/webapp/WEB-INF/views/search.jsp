<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Search Flights from ${originIATAId} - ${originAirport} - ${originCity} to ${destinationIATAId} - ${destinationAirport} - ${destinationCity} on ${departureDate}</title>

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
    <div class="container">
        <div class="row my-4">
            <div class="col-12">
                <i class="fas fa-plane"></i> Flights from <strong>${originIATAId} - ${originAirport} - ${originCity}</strong> to <strong>${destinationIATAId} - ${destinationAirport} - ${destinationCity}</strong> on ${departureDate}
            </div>
        </div>
        <div class="row justify-content-lg-center mb-4 bg-danger text-light font-weight-bold">
            <div class="col-1 py-3">Flight ID</div>
            <div class="col-3 py-3">Departure Time</div>
            <div class="col-3 py-3">Arrival Time</div>
            <div class="col-3 py-3">Price</div>
            <div class="col-2 py-3"> </div>
        </div>

        <c:forEach items="${ flightTickets }" var="flightTicket">
            <form class="mb-3" method="GET" action="book">
                <div class="row">
                    <div class="col-1">
                        <label for="flightTicketId${flightTicket[0]}" class="sr-only">Flight Ticket ID</label>
                        <input type="text" readonly class="form-control-plaintext" id="flightTicketId${flightTicket[0]}" value="${flightTicket[0]}" name="flightTicketId">
                    </div>
                    <div class="col-3">
                        <label for="departureTime${flightTicket[1]}" class="sr-only">Departure Time</label>
                        <input type="text" readonly class="form-control-plaintext" id="departureTime${flightTicket[1]}" value="${flightTicket[1]}" name="departureTime">
                    </div>
                    <div class="col-3">
                        <label for="arrivalTime${flightTicket[2]}" class="sr-only">Arrival Time</label>
                        <input type="text" readonly class="form-control-plaintext" id="arrivalTime${flightTicket[2]}" value="${flightTicket[2]}" name="arrivalTime">
                    </div>
                    <div class="col-3">
                        <label for="price${flightTicket[3]}" class="sr-only">price</label>
                        <input type="text" readonly class="form-control-plaintext" id="price${flightTicket[3]}" value="${flightTicket[3]}" name="price">
                    </div>
                    <div class="col-2">
                        <button type="submit" class="btn btn-success">Book Flight</button>
                    </div>
                </div>

            </form>
        </c:forEach>

    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js" integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E=" crossorigin="anonymous"></script>
</body>



</html>
