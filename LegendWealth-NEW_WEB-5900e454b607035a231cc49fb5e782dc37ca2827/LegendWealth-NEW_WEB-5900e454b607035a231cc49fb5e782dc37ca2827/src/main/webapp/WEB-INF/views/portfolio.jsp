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
	
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
      <!-- c3 -->
      <script src="https://d3js.org/d3.v5.min.js"></script>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.6.10/c3.css" />
      <script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.6.10/c3.min.js"></script>
      <title>LegendWealth</title>
   </head>
   <body>
<%--    ${portfolio} --%>
<%--    ${portfolioAssetSummary} --%>
<%--    ${portfolio_id} --%>
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
            <c:if test="${success_message != null}">
				<div class="alert alert-success alert-dismissible fade show" role="alert">
				${success_message}
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
				</div>
			</c:if>
            <h2>${portfolio.name}</h2>
            <p class="lead">Current Net Asset Value: USD <span id="portfolioSumTotal"></span></p>
            <a class="btn btn-danger" href="select-asset?id=${portfolio.portfolioId}" role="button">Buy New Asset</a>
         </div>
         <div class="row">
            <div class="col-sm-7">
               <table class="table table-hover">
                  <thead>
                     <tr>
                        <th scope="col">Asset</th>
                        <th scope="col">Type</th>
                        <th scope="col">Unit Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Value</th>
                        <th scope="col">Action</th>
                     </tr>
                  </thead>
                  <tbody id="portfolio"></tbody>
               </table>
            </div>
            <div class="col-sm-5">
               <div id="chart"></div>
            </div>
         </div>
      </div>
      <script>
         let jsonData = ${portfolioAssetSummary};
         let data = {};
         let sites = [];
         jsonData.forEach(function(e) {
             sites.push(e.assetCode);
             data[e.assetCode] = e.value;
         })    
         
         
         let chart = c3.generate({
             data: {
                 json: [ data ],
                 keys: {
                     value: sites
                 },
                 type: 'donut'
             },
             pie: {
                 label: {
                     format: function (value, ratio, id) {
                         return d3.format('$,.0f')(value);
                     }
                 }
             }
         });
         
         let sumTotal = 0;
         let tbody = document.querySelector("#portfolio");
         jsonData.forEach(p => {
         	let row = document.createElement("tr");
         	let td1 = document.createElement("td");
         	td1.appendChild(document.createTextNode(p.assetCode));
         	row.appendChild(td1);
         	let td2 = document.createElement("td");
         	td2.appendChild(document.createTextNode(p.assetDescription));
         	row.appendChild(td2);
         	let td3 = document.createElement("td");
         	td3.appendChild(document.createTextNode("$" + p.unitPrice.toLocaleString('en')));
         	row.appendChild(td3);
         	let td4 = document.createElement("td");
         	td4.appendChild(document.createTextNode(p.quantity.toLocaleString('en')));
         	row.appendChild(td4);
         	let td5 = document.createElement("td");
         	td5.appendChild(document.createTextNode("$" + p.value.toLocaleString('en')));
         	row.appendChild(td5);
         	tbody.appendChild(row);
         	let td6 = document.createElement("td");
	         	let changeBtn = document.createElement("a");
	         	changeBtn.setAttribute("role", "button");
         	if (p.assetDescription != "Cash") {
	         	changeBtn.appendChild(document.createTextNode("Buy/Sell"));
	         	changeBtn.setAttribute("class", "btn btn-sm btn-outline-danger");
	          	changeBtn.setAttribute("href", "trade-detail?portfolio_id=" + p.portfolioId + "&asset_id=" + p.assetId);
         	} else {
         		changeBtn.appendChild(document.createTextNode("Coming Soon"));
	         	changeBtn.setAttribute("class", "btn btn-sm btn-outline-secondary");
         		changeBtn.setAttribute("disabled", "");
         	}
         	td6.appendChild(changeBtn);
         	row.appendChild(td6);
         	tbody.appendChild(row);
         	
         	sumTotal += p.value;
         });
         

         document.querySelector("#portfolioSumTotal").innerHTML = sumTotal.toLocaleString('en');
      </script>
   </body>
</html>