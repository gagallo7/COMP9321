<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Popcorn Troll</title>
</head>
<body>

    
    <div class="info">
    
    </div>
    <%@ include file="header.html"%>
    
    <div id="greetings">
    (IF LOGGED) Hello 'username', prepare your popcorn, but don't feed the troll!
    </div>
    
		<ul class="services">
			<li><a href="login.jsp">Login</a></li>
			<li><a href="register.jsp">Register</a></li>
			<li><a href="search.jsp">Search (USER ONLY)</a></li>
			<li><a href="booking.jsp">Bookings (USER ONLY)</a></li>
		</ul>

	<h2 class="highlight">Now Showing</h2>
	ORDER BY RATING

	<ul class="movieList">
		<li>
			<div class="movie">
				<a href="details.jsp"><img class="mini" alt="poster"
					src="img/movie1.jpg"></a>
				<p>
					<a href="details.jsp">Movie 1</a>
				</p>
				<div class="rating">☆☆☆☆☆</div>
			</div>

			<div class="movie">
				<a href="details.jsp"><img class="mini" alt="poster"
					src="img/movie1.jpg"></a>
				<p>
					<a href="details.jsp">Movie 2</a>
				</p>
				<div class="rating">☆☆☆☆☆</div>
			</div>

			<div class="movie">
				<a href="details.jsp"><img class="mini" alt="poster"
					src="img/movie1.jpg"></a>
				<p>
					<a href="">Movie 2</a>
				</p>
				<div class="rating">☆☆☆☆☆</div>
			</div>
		</li>

	</ul>


	<h2 class="highlight">Coming Soon</h2>
	ORDER BY RELEASE DATE
	<ul class="movieList">
		<li>
			<div class="movie">
				<a href=""><img class="mini" alt="poster"
					src="${pageContext.request.contextPath}/img/movie1.jpg"></a>
				<p>
					<a href="">Movie 1</a>
				</p>
				<!-- 				<div class ="rating">☆☆☆☆☆</div> -->
			</div>
		</li>
	</ul>

</body>
</html>