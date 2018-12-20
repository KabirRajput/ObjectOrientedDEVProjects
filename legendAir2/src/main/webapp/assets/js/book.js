		const passFormBtn = document.getElementById("passFormBtn");
		const passForm = document.getElementById("passForm");
        const addPassengerBtn = document.getElementById("addPassenger");

		passFormBtn.addEventListener("click", function (e) {
		    e.preventDefault();
		    if (passForm.style.display === "none") {
		        passForm.style.display = "block";
		        passFormBtn.innerHTML = "<i class=\"fas fa-minus\"></i>"
		    } else {
		        passForm.style.display = "none";
		        passFormBtn.innerHTML = "<i class=\"fas fa-plus\"></i>"
		    }
		});

addPassengerBtn.addEventListener("click", function(e) {
    
});