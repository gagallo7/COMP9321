<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Register in Popcorn Troll</title>
<script src="validation.js"></script> 

</head>
<body>
	<%@ include file="/header.html"%>
	
	<h1>Registration</h1>
	
	<strong id="info"></strong>
	<form action="test" name="reg" method="post"
		onsubmit="return validate();">
		<p>
			Username: <input type="text" name="username"> <output style="position: absolute; color: gray;">4 or more characters</output>
		</p>
		<p>
			Password: <input type="password" name="password" id="p" onblur="x.value=checkPass(p,rp);"> <output style="position: absolute; color: gray;">4 or more characters</output>
		</p>
		<p>
			Repeat Password: <input onblur="x.value=checkPass(p,rp);"
				type="password" id="rp">
			<output id="x" style="position: absolute; color: red;"></output>
		</p>
		<p>
			E-mail: <input type="email" name="email">
		</p>
		<p>
			<input type="submit" value="Register">
		</p>
	</form>
</body>
</html>