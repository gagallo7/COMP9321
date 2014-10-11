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

	IF IS LOGGED AS "ADMIN" AND THE MOVING IS "NOW SHOWING"
	<h2>Associate this movie with a cinema</h2>
	<form action="test" method="post">
	   <p>
		Which cinema? <select name="cinema">
			<option value="1">Cine 1</option>
		</select>
		</p>
		<p>
            Date (DD/MM/YYYY): <input type="text" name="showingDate" pattern="[0-3][0-9]/[0-1][0-9]/201[4-9]" required="required">
        </p>
        <p>
            Time (HH:MM): <input type="text" name="showingTime" pattern="[0-2][0-9]:[0-5][0-9]" required="required">
        </p>
        <p>
            <input type="submit" value="Create Session">
        </p>
	</form>



	<h2>Butterfly Effect</h2>
	<img class="large" alt="poster" src="img/movie1.jpg">
	<div class="rating">☆☆☆☆☆</div>
	<div class="movieInfo">
		<p>
			<strong>Genre:</strong> Drama
		</p>
		<p>
			<strong>Director:</strong> Chris na Lan
		</p>
		<p>
			<strong>Actors:</strong> A., B. and C.
		</p>
		<p>
			<strong>Synopsis:</strong> blablablabla blabla blabla blabla blabla
			blabla blabla blabla blabla blabla blabla blabla blabla blabla blabla
			blabla blabla blabla blabla blabla blabla blabla
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

	<br />
	<br />
	<br />
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