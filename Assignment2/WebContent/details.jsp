<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Searching for Movies</title>
</head>
<body>
	<%@ include file="header.html"%>

	<h2>Butterfly Effect</h2>
	<img class="large" alt="poster" src="img/movie1.jpg">
	<div class="rating">☆☆☆☆☆</div>
	<div class="movieInfo">
		<p>
			<strong>Genre:</strong> Drama
		</p>
		<p>
			<strong>Actors:</strong> A., B. and C.
		</p>
		<p>
			<a href="">More Details</a>
		</p>
	</div>

	<h1>If the movie has been released.</h1>

	<form action="test" name="booking">
		<table>
			<caption>Showtimes</caption>
			<thead>
				<tr>
					<th>Cinema</th>
					<th>Showtime</th>
					<th>Choose</th>
				</tr>
			</thead>
		</table>
		<input type="submit" value="Book!">
	</form>

<br/><br/><br/>
	<table>
		<caption>Reviews</caption>
		<thead>
			<tr>
				<th>Rating</th>
				<th>Nickname</th>
				<th>Review</th>
			</tr>
		</thead>
	</table>
	<form action="test">
		<p>Review this movie:</p>
		<textarea rows="10" cols="50" name="review"></textarea>
		<br /> Your rating:
		<div class="rating">☆☆☆☆☆</div>
		<p>
			<input type="submit" value="Send Review">
		</p>
	</form>
</body>
</html>