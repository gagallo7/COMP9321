<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="edu.unsw.comp9321.model.*"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Your Popcorn Troll Profile</title>

<script type="text/javascript">
	function checkPass(a, b) {
		if (a.value != b.value) {
			//          return a.value + " " + b.value + "Passwords don't check!";
			return "Passwords differ!";
		}
		return " ";
	}

	function validate() {
		if (checkPass(document.getElementById("p"), document
				.getElementById("rp")) != " ") {
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
</script>
</head>
<body>
	<%@ include file="header.html"%>
	<strong id="info"></strong>
	<h3>
		Editing profile of Username:
		<output name="username">${user.username}</output>
	</h3>
	<form action="control" name="upd" method="post"
		onsubmit="return validate();">

        <p>
            Nickname: <input type="text" name="nickname" value="${user.nickname}">
            <output id="x" style="position: absolute; color: gray;">4 or
                more characters</output>
        </p>
        <p>
            First Name: <input type="text" name="firstName" value="${user.firstName }">
        </p>
        <p>
            Last Name: <input type="text" name="lastName" value="${user.lastName }">
        </p>
		<p>
			Password: <input type="password" name="password" id="p">
			<output id="x" style="position: absolute; color: gray;">4 or
				more characters</output>
		</p>
		<p>
			Repeat Password: <input onblur="x.value=checkPass(p,rp);"
				type="password" id="rp">
			<output id="x" style="position: absolute; color: red;"></output>
		</p>
		<p>
			E-mail: <input type="email" name="email" value="${user.email }">
		</p>
		<p>
			<input type="hidden" name="action" value="editProfile"/> 
			<input type="submit" value="Update">
		</p>
	</form>
</body>
</html>