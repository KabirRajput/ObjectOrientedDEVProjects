            var keyCounter = 0;

            $(document).ready(function () {
                $(".datepicker").each(function () {
                    $(this).datepicker({
                        dateFormat: "dd-M-y"
                    });
                });

                requestAirports("#origin", "airports-origin");
                requestAirports("#destination", "airports-destination");
            });

            function capitalize(word) {
                return word.charAt(0).toUpperCase() + word.slice(1);
            }

            function addTextToField(id) {
                var el = document.getElementById(id);
                el.value = this.innerText;
            }

            function requestAirports(fieldId, outputId) {
                $(fieldId).keyup(function (e) {
                    var city = e.target.value;
                    keyCounter++;
                    if (keyCounter > 3) {
                        $.ajax({
                            type: "POST",
                            cache: false,
                            url: document.location.href + "airport",
                            data: {
                                city: capitalize(city)
                            },
                            success: function (airports) {
                                if (airports.length > 0) {
                                    resetOutputAirport(outputId);
                                    var airportsUl = document.createElement("ul");
                                    airports.forEach(function (airport) {
                                        var airportLi = document.createElement("li");
                                        airportLi.innerHTML = "<strong>" + airport[1] + "</strong>" + ", " + airport[0] + ", " + "<strong>" + airport[2] + "</strong>";
                                        airportLi.addEventListener("click", function (e) {
                                            e.stopPropagation();
                                            $(fieldId).val(this.innerText);
                                        });
                                        airportsUl.appendChild(airportLi);
                                    });
                                    airportsUl.className = "airports-list-ul";
                                    var airportsOutput = document.getElementById(outputId);
                                    airportsOutput.innerHTML = "";
                                    airportsOutput.style.display = "block";
                                    airportsOutput.appendChild(airportsUl);
                                }
                            }
                        });
                    }
                });

                $(`#${outputId}`).mouseleave(function () {
                    resetOutputAirport(outputId);
                });
            }

            function resetOutputAirport(outputId) {
                var airportsOutput = document.getElementById(outputId);
                airportsOutput.innerHTML = "";
                airportsOutput.style.display = "none";
            }
