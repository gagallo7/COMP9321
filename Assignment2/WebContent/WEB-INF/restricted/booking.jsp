<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="edu.unsw.comp9321.model.*"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Booking Movies</title>
</head>
<body>
	<%@ include file="/header.html"%>
	<table>
		<caption>Your booking list</caption>
		<thead>
			<tr>
				<th>Session</th>
				<th>Cinema</th>
				<th>Showtime</th>
				<th># of Tickets</th>
			</tr>
		</thead>
		<c:forEach var="book" items="${bookings}">
			<tr>
				<td>${book.cinemaSession.id}</td>
				<td>${book.cinemaSession.cinema.location}</td>
				<td>${book.cinemaSession.showTime }</td>
				<td>${book.numberOfTickets}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>