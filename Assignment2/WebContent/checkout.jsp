<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="edu.unsw.comp9321.model.*"%>   
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
	<strong>Movie: </strong> ${cinemaSession.movieId}
	<br><strong>Cinema: </strong> ${cinemaSession.cinema.location}
	<br><strong>Showtime: </strong> ${cinemaSession.showTime }
	
	<h3>Payment Details</h3>
	<form action="control" onchange="x.value=t.value*12;" name="payment" method="post">
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