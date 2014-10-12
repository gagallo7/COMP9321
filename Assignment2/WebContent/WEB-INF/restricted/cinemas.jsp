<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Cinema Administration</title>

</head>
<body>
	<strong id="info"></strong>
	<%@ include file="/header.html"%>
	<h1>Cinema Configuration</h1>

	<h2>Add a cinema</h2>

	<form action="control"  method="post">
		<p>
			Location: <input type="text" name="location" required="required">
		</p>
		<p>
			Seating Capacity: <input type="number" name="seatCap" value=20 min=10
				max=1000 required="required">
		</p>
		
		<table>
			<tr>
		   		<td align="right">Amenities: </td>
				<td><input type="checkbox" name="amenities" value="atm">ATM </td>	
			</tr>	
			<tr>
				<td></td>
				<td><input type="checkbox" name="amenities" value="widescreen">Widescreen</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="checkbox" name="amenities" value="snack">Snack Bar</td>
			</tr>				
			<tr>
				<td></td>
				<td><input type="checkbox" name="amenities" value="restaurant">Restaurant</td>
			</tr>
		</table>
		<br>
		
		<input type="hidden" name="action" value="createCinema"/> 
		<input type="submit" value="Add cinema">
	</form>

	<!-- The  cinema  owner   can     add     movies  to  the     site    through    
	 a   form    input   that    takes   the       -->
	<!-- following    data:   Title,  Poster  (JPG/PNG),  list    of  actors  and     actresses,  Genre(s)    
	that    it belongs  to,     Director,   Short   Synopsis    (100    words),     Age     rating.      
	Romance, Horror, Thriller,Comedy, Drama, Biopic, Action.
	 -->
	<br>
	<h2>Add movie</h2>
	<form action="control" method="post" enctype="multipart/form-data"> <!-- multipart/form-dat to be able to manipulate the image  -->
		<p>
			Title: <input type="text" name="title" required="required">
		</p>
		<p>
			Poster: <input type="file" name="upfile" required="required" >
<!-- 			accept="image/png,image/jpg" -->
		</p>
		<p>
			Synopsis:
			<textarea name="synopsis" rows="5" cols="40" maxlength="100"></textarea>
		</p>
		<p>
			Director: <input type="text" name="director" required="required">
		</p>
		<p>
			Actors and Actresses (separated by comma): <input type="text"
				name="actors" required="required">
		</p>
		<p>
			Age Rating: <input type="number" name="ageRating" value=12 min=0
				max=30 required="required">
		</p>
		<p>
			Release Date (DD/MM/YYYY): <input type="text" name="releaseDate"
				pattern="[0-3][0-9]/[0-1][0-9]/201[4-9]" required="required">
		</p>

		<table>
			<tr>
		   		<td align="right">Genre: </td>
				<td><input type="radio" name="genre" value="romance">Romance</td>	
			</tr>	
			<tr>
				<td></td>
				<td><input type="radio" name="genre" value="horror">Horror</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="genre" value="thriller">Thriller</td>
			</tr>				
			<tr>
				<td></td>
				<td><input type="radio" name="genre" value="comedy">Comedy</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="genre" value="drama">Drama</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="genre" value="biopic">Biopic</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="genre" value="action">Action</td>
			</tr>
		</table>
		<br>
		
		<input type="hidden" name="action" value="addMovie"/> 
		<input type="submit" value="Add Movie">
	</form>

</body>
</html>
