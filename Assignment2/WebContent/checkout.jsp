<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Checkout Details</title>
</head>
<body>
	<%@ include file="header.html"%>

	<h2>Booking details</h2>
	<strong>Movie: </strong> Butterfly Effect
	<br><strong>Cinema: </strong> FirstCinSin
	<br><strong>Showtime: </strong> 15/10/2014
	
	<h3>Payment Details</h3>
	<form action="test" onchange="x.value=t.value*12;" name="payment" method="post">
	    <p>
            Number of tickets: <input  name="t" type="number" min=1 max=20 width="2" name="tickets" required="required">
            ( AUD$ <output id="x"></output> ) 
        </p>
		<p>
			Credit Card Number: <input type="text" pattern="[0-9]*" name="cardNo" required="required"> 
		</p>
		<p>
			CVV Number: <input type="text" pattern="[0-9]{3}" name="cardNo" required="required">
		</p>
		<p>
			<input type="submit" value="Checkout">
		</p>
	</form>
</body>
</html>