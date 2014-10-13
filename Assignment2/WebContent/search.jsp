<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Search for Movies</title>
<script src="/validation.js"></script>
</head>
<body>
	<%@ include file="/header.html"%>

	<h2>Search for Movies</h2>

	<strong id="info"></strong>
	<form action="control" method="get" name="searchForm"
		onsubmit="return checkLength(title, 1, info);">

		<p>
			By title: <input type="search" name="title"> <input
				type="submit" value="Search">
		</p>
		<p>
			Or by genre: <select name="genre"
				onchange="if (this.value.length > 0 ) this.form.submit();">
				<option value="">Choose</option>
				<option value="action">Action</option>
				<option value="biopic">Biopic</option>
				<option value="comedy">Comedy</option>
				<option value="drama">Drama</option>
				<option value="horror">Horror</option>
				<option value="romance">Romance</option>
				<option value="thriller">Thriller</option>
			</select>
		</p>
		<input type="hidden" name="action" value="searchMovie"/> 
	</form>
</body>
</html>