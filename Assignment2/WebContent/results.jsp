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
	
	<h2>Showing 2 results</h2>

	<div class="result">
		<div class="movie2">
			<a href="details.jsp"><img class="mini" alt="poster" src="img/movie1.jpg"></a>
			<br> <a href="details.jsp">Movie 1</a>
			<div class="rating2">☆☆☆☆☆</div>
		</div>

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
	</div>

<div class="result">
        <div class="movie2">
            <a href=""><img class="mini" alt="poster" src="img/movie1.jpg"></a>
            <br> <a href="details.jsp">Movie 2</a>
            <div class="rating2">☆☆☆☆☆</div>
        </div>

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
    </div>
</body>
</html>