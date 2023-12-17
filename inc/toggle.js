window.addEventListener("load", function() {
	let user = this.document.querySelector(".fa-user");
	let div_con = this.document.getElementById("div_con");
	let div_dash = this.document.getElementById("allpage");

	user.addEventListener("click", function() {
		div_con.style.display = "block";
		div_dash.style.filter = "blur(2px)";
	})
	this.document.addEventListener("click", function(e) {
		if (e.target == div_con) {
			div_con.style.display = "none";
			div_dash.style.filter = "blur(0px)";
		}
	})


	/*
		
	*/
	function $_(identif) {
		return document.getElementById(identif);
	}

	function $Q_(identif) {
		return document.querySelector(identif);
	}

	var blocSignIn = $_("bloc_signin");
	var secondpart = $_("secondBloc");

	function myMove() {
		var pos = -20;
		var id = setInterval(frame, 5);
		var visible = true;

		function frame() {
			if (visible) {
				blocSignIn.style.display = "block";
				$Q_("#bloc_signup button").style.display = "none";
				visible = false;
			}
			if (pos == 50) {
				clearInterval(id);
			} else {
				pos = pos + 10;
				blocSignIn.style.bottom = pos + "px";
			}
		}
	}
	document.getElementById("signin").addEventListener("click", function() {
		myMove();
	});
	document.getElementById("cancel").addEventListener("click", function() {
		blocSignIn.style.display = "none";
		document.querySelector("#bloc_signup button").style.display = "block";
	});
	document.getElementById("signup").addEventListener("click", function() {
		secondpart.classList.toggle("slide");
	});
	document.querySelector("#secondBloc i").addEventListener("click", function() {
		secondpart.classList.toggle("slide");
	});

})
