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

 <!-- c3 -->
      <script src="https://d3js.org/d3.v5.min.js"></script>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.6.10/c3.css" />
      <script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.6.10/c3.min.js"></script>
      
<title>LegendWealth</title>
</head>
<body>
<%-- ${active_broker} --%>
<%-- ${portfolio} --%>
<%-- ${asset} --%>
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
        <p class="lead">Current Net Asset Value: USD <span id="portfolioSumTotal"></span></p>
        <p class="lead">"Every man lives by exchanging." - Adam Smith</p>
</div>
      
<div class="row">
        <div class="col-md-6 order-md-2 mb-6">
          <h4 class="d-flex justify-content-between align-items-center mb-3">
            <span>Portfolio Mix</span>
          </h4>
          <div id="chart"></div>
        </div>
        <div class="col-md-6 order-md-1">
          <h4 class="d-flex justify-content-between align-items-center mb-3">
          Selected asset : ${asset.code}
          <span class="badge badge-warning badge-pill">${asset.assetType.description}</span>
          </h4>
          <form class="needs-validation" novalidate="" method="POST" action="buy-asset">
            <input type="hidden" name="trade_form_portfolio_id" value="${portfolio.portfolioId}" />
            <input type="hidden" name="trade_form_asset_id" value="${asset.assetId}" />
<div class="row">
              <div class="col-md-6">
              </div>
              <div class="col-md-6 text-right">
                <label for="country">Cash Available (USD)</label>
                <h4 id="cash-original">0</h4>
              </div>
            </div>           
            <hr class="mb-4">

<div class="row">
              <div class="col-md-4 mb-3">
                <label for="country">Unit Price</label>
                <input type="text" class="form-control" id="zip" placeholder="$${asset.price}" disabled>
              </div>
              <div class="col-md-4 mb-3">
                <label for="state">Quantity</label>
                <input type="number" class="form-control" name="trade_form_quantity" id="currentQuantity" aria-describedby="emailHelp" placeholder="Quantity" min="0" max="" required>
				<input id="quantityChangedHidden" type="hidden" name="trade_form_quantityTotal" value="0" />
                <div class="invalid-feedback">
                  You can buy <span id="maxQuantity"></span> ${asset.code}.
                </div>
              </div>
              <div class="col-md-4 mb-3 text-right">
                <label for="total">Asset Total</label>
                <h4 id="currentAssetPriceTotal" class="text-right mt-1" >0</h4>
                <input id="cashChangedHidden" type="hidden" name="trade_form_priceTotal" value="0" />
              </div>
            </div>
<div class="row text-muted">
              <div class="col-md-6">
              <h5>Quantity Changed</h5>
              </div>
              <div class="col-md-6">
                <h5 id="quantity-changed" class="text-right">0</h5>
              </div>
            </div>
  <hr class="mb-4">           
<div class="row">
              <div class="col-md-7">
              <h4>Cash Changed (USD)</h4>
              </div>
              <div class="col-md-5">
                <h4 id="cash-changed" class="text-right">0</h4>
              </div>
            </div>
<!--   <hr class="mb-4">             -->
<!--   <div class="row"> -->
<!--               <div class="col-md-6"> -->
<!--               <h5>Final Cash (USD)</h5> -->
<!--               </div> -->
<!--               <div class="col-md-6"> -->
<!--                 <h5 id="cash-final" class="text-right">0</h5> -->
<!--               </div> -->
<!--             </div> -->
  <hr class="mb-4">
  <button type="submit" class="btn btn-danger btn-lg btn-block">Buy</button>
          </form>
        </div>
      </div>      
      
   
</div>
<script>
         let jsonData = ${portfolioAssetSummary};
         let assetToBeChanged = "${asset.code}";
         let initQuantity = (jsonData.filter(a => a.assetCode == assetToBeChanged).length)? jsonData.filter(a => a.assetCode == assetToBeChanged)[0].quantity : 0;
         let initAssetPriceTotal = (jsonData.filter(a => a.assetCode == assetToBeChanged).length)? jsonData.filter(a => a.assetCode == assetToBeChanged)[0].value : 0;
         let initTotalCash = jsonData.filter(a=>a.assetDescription =="Cash")[0].value;
		 let currentQuantity = 0;
		 let currentAssetPriceChanged = 0;
		 let currentAssetPriceTotal = 0;
		 let quantityChanged = 0;
         let cashChanged = 0;
         let finalTotalCash = 0;
         

         document.querySelector("#portfolioSumTotal").innerHTML = jsonData.reduce(function (accumulator, currentValue) {
      	    return accumulator + currentValue.value;
         },0);
		 
		 let mode = (jsonData.filter(a => a.assetCode == assetToBeChanged).length)? 'change' : 'buy';
		 console.log(mode);
         
         let data = {};
         let sites = [];
         
         jsonData.forEach(function(e) {
             sites.push(e.assetCode);
             data[e.assetCode] = e.value;
         })    
         
         
         let chart = c3.generate({
        	 size: {
        	        height: 400,
        	        width: 400
        	    },
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
         
         let quantityInput = document.querySelector("#currentQuantity");
         let currentAssetPriceTotalTxt = document.querySelector("#currentAssetPriceTotal");
         let quantityChangedHidden = document.querySelector("#quantityChangedHidden");
         let cashChangedHidden = document.querySelector("#cashChangedHidden");
         let cashOriginalTxt = document.querySelector("#cash-original");
         let quantityChangedTxt = document.querySelector("#quantity-changed");
         let cashChangedTxt = document.querySelector("#cash-changed");
         
      	 // Set initial Original Cash
         cashOriginalTxt.innerHTML = Math.round(initTotalCash* 100/100).toLocaleString('en');
         
         // Set initial quantity
         quantityInput.setAttribute("max", parseInt((initTotalCash+initAssetPriceTotal)/${asset.price}, 10));
         quantityInput.value = initQuantity;
         
         document.querySelector("#maxQuantity").innerHTML = parseInt((initTotalCash+initAssetPriceTotal)/${asset.price}, 10);
         
         // Set changed
         cashChangedTxt.innerHTML = cashChanged;
         quantityChangedTxt.innerHTML = quantityChanged;
         
         // Set initial sum total
         currentAssetPriceTotalTxt.innerHTML = Math.round(initAssetPriceTotal* 100/100).toLocaleString('en');
         
         console.log("assetToBeChanged: " + assetToBeChanged);	
    	 console.log("initQuantity: " + initQuantity);
    	 console.log("initAssetPriceTotal: " + initAssetPriceTotal);
    	 console.log("initTotalCash: " + initTotalCash);
         
         quantityInput.addEventListener("input", () => {
        		 
        	 currentQuantity = quantityInput.value;
        	 quantityChanged = currentQuantity - initQuantity;
        	 
        	 currentAssetPriceChanged = quantityChanged * ${asset.price};
        	 currentAssetPriceTotal = currentQuantity * ${asset.price};
        	 
        	 console.log("currentQuantity :" + currentQuantity);
        	 console.log("currentAssetPriceTotal :" + currentAssetPriceTotal);
        	 
        	 cashChanged = initAssetPriceTotal - currentAssetPriceTotal;
        	
        	 
        	 console.log("quantityChanged :" + quantityChanged);
        	 console.log("cashChanged :" + cashChanged);
        	 
        	 finalTotalCash = initTotalCash + cashChanged;
        	 
        	 if (finalTotalCash >= 0) {
	        	 cashOriginalTxt.innerHTML = parseFloat(Math.round(finalTotalCash)).toFixed(2);
	        	 
	        	 currentAssetPriceTotalTxt.innerHTML = parseFloat(Math.round(currentAssetPriceTotal)).toFixed(2);
	        	 
	        	 quantityChangedHidden.setAttribute("value", parseFloat(Math.round(quantityChanged)).toFixed(2));
	        	 cashChangedHidden.setAttribute("value", parseFloat(Math.round(cashChanged)).toFixed(2));
	        	 
	        	 quantityChangedTxt.innerHTML = parseFloat(Math.round(quantityChanged)).toFixed(2);
	        	 quantityChangedTxt.style.color = (quantityChanged >= 0)? "green" : ((quantityChanged < 0) ? "red" : "black");
	             
	        	 cashChangedTxt.innerHTML = parseFloat(Math.round(cashChanged)).toFixed(2);
	        	 cashChangedTxt.style.color = (cashChanged >= 0)? "green" : ((cashChanged < 0) ? "red" : "black");
	                   	 
	        	 
	        	 data["USD"] = finalTotalCash;
	        	 data[assetToBeChanged] = currentAssetPriceTotal;
	        	 
	        	 
	        	 if (!sites.find(function(e) {return e == assetToBeChanged;})) {
	        		 sites.push(assetToBeChanged);
	        	 }
	        	 chart.load({json: data , unload: data});
         
        	 } else { // Not enough cash
        		 cashOriginalTxt.innerHTML = 0;
        		 cashChangedTxt.innerHTML = "-";
        		 quantityChangedTxt.innerHTML = "-";	
        		 currentAssetPriceTotalTxt.innerHTML = "-";
        	 }
          
        	 
         
         });
         
         
         
      // Example starter JavaScript for disabling form submissions if there are invalid fields
         (function() {
           'use strict';
           window.addEventListener('load', function() {
             // Fetch all the forms we want to apply custom Bootstrap validation styles to
             var forms = document.getElementsByClassName('needs-validation');
             // Loop over them and prevent submission
             var validation = Array.prototype.filter.call(forms, function(form) {
               form.addEventListener('submit', function(event) {
                 if (form.checkValidity() === false) {
                   event.preventDefault();
                   event.stopPropagation();
                 }
                 form.classList.add('was-validated');
               }, false);
             });
           }, false);
         })();   
         
      </script>
</body>
</html>