<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="edu.unsw.comp9321.model.*"%>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Searching for Movies</title>
</head>
<body>
	<%@ include file="header.html"%>

	<!-- -------------------OWNER ADD SHOWTIME AND CINEMAS------------------- -->
	<c:if test="${UserRole eq 'manager'}">
		<h2>Associate this movie with a cinema</h2>
		<form action="control" method="post">
			<p>
				Which cinema? 
				<select name="cinemaId">
					<c:forEach var="cinema" items="${cinemas}">
						<option value="${cinema.id}">${cinema.location}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				Date (DD/MM/YYYY): <input type="text" name="showDate"
					pattern="[0-3][0-9]/[0-1][0-9]/201[4-9]" required="required">
			</p>
			<p>
				Time (HH:MM): <input type="text" name="showTime"
					pattern="[0-2][0-9]:[0-5][0-9]" required="required">
			</p>
			<p>
				<input type="hidden" name="movieId" value="${movie.id}"/> 
				<input type="hidden" name="action" value="addCinemaSession"/> 
				<input type="submit" value="Create Session">
			</p>
		</form>
	</c:if>
	<!-- ---------------------------------------------------------------------- -->
	
	<!-- --------------------------MOVIE DETAILS------------------------------- -->
	<h2>${movie.title}</h2>
	<img class="large" alt="poster" src="img/${movie.urlPost}">
	<div class="rating">
		<c:forEach begin="1" end="${movie.rating}">
			☆
		</c:forEach>
	</div>
	<div class="movieInfo">
		<p>
			<strong>Genre:</strong> ${movie.genre}
		</p>
		<p>
			<strong>Director:</strong> ${movie.director}
		</p>
		<p>
			<strong>Actors:</strong> A., B. and C.
		</p>
		<p>
			<strong>Synopsis:</strong> ${movie.synopsis}
		</p>
	</div>
	<!-- ---------------------------------------------------------------------- -->
	
	
	<!-- ------------IF THE MOVIE HAVE BEEN RELEASED--------------------------- -->
	<c:if test="${movie.nowShowing eq 1}">
	<!-------------------------SESSIONs---------------------------------------- -->
		<form action="control" onchange="x.value=numtickets.value*12;" name="booking" method="post">
			<table>
				<caption>Sessions - Showtimes and Cinemas</caption>
				<thead>
					<tr>
						<th>Cinema</th>
						<th>Showtime</th>
						<c:if test="${UserRole eq 'user'}">
							<th>Choose</th>
						</c:if>
					</tr>
				</thead>
				<c:forEach var="session" items="${sessions}">
					<tr>
						<td>${session.cinema.location}</td>
						<td>${session.showTime}</td>
						<c:if test="${UserRole eq 'user'}">
							<td><input type="radio" name="sessionId" value="${session.id}"></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${UserRole eq 'user'}">
				<p>Number of tickets: 
					<input type="number" name="numTickets" min=1 max=20 width="2" required="required">
	            	( AUD$ <output id="x"></output> ) 
	        	</p>
        	</c:if>
			<input type="hidden" name="action" value="bookSession"/>
			<input type="submit" value="Book!">
		</form>
		<br /><br /><br />
	<!-- ---------------------------------------------------------------------- -->
	
	<!-- -----------------------------REVIEWS---------------------------------- -->	
		<table>
			<caption>Reviews</caption>
			<thead>
				<tr>
					<th>Rating</th>
					<th>Nickname</th>
					<th>Review</th>
				</tr>
				<c:forEach var="review" items="${reviews}">
					<tr>
						<td>${review.rating}</td>
						<td>${review.username}</td>
						<td>${review.text}</td>
					</tr>
				</c:forEach>
			</thead>
		</table>
		
		<form action="control" method="post">
			<p>Review this movie:</p>
			<textarea rows="10" cols="50" name="review"></textarea>
			<br /> Your rating:
			<div class="rating">☆☆☆☆☆
				<input type="number" name="rating" min=1 max=5 required="required">
			</div>
			<p>
				<input type="hidden" name="movieId" value="${movie.id}"/> 
				<input type="hidden" name="action" value="reviewMovie"/>
				<input type="submit" value="Send Review"/>
			</p>
		</form>
	<!-- ---------------------------------------------------------------------- -->
	</c:if>
</body>
</html>