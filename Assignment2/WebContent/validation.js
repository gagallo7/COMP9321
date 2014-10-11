function checkPass(a, b) {
	if (a.value != b.value) {
		// return a.value + " " + b.value + "Passwords don't check!";
		return "Passwords differ!";
	}
	return " ";
}

function capitaliseFirstLetter(string)
{
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function checkLength(a, min, info) {
//	alert ( a.value.length + min + info.value );
	if (a.value.length < min) {
		info.innerHTML	 = capitaliseFirstLetter (a.name) + " has less than " + min + " characters";
		return false;
	}
	return true;
}

function validate() {
	if (checkPass(document.getElementById("p"), document.getElementById("rp")) != " ") {
		alert("Passwords differ!");
		return false;
	}

	var i1 = document.forms["reg"]["username"].value.length > 3;
	var i2 = document.forms["reg"]["password"].value.length > 3;
	var i4 = document.forms["reg"]["email"].value.length > 4;

	var s = i1 + i2 + i4;

	if (s < 3) {
		document.getElementById("info").innerHTML = "Verify your input!";
		return false;
	}

	return true;
}