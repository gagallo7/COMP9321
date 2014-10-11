<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Popcorn Troll</title>
</head>
<body>


	<div class="info"></div>
	<%@ include file="header.html"%>

	<c:if test="${UserRole eq 'user'}">
		<div id="greetings">Hello ${username}, prepare your popcorn, but
			don't feed the troll!</div>
	</c:if>

	<ul class="services">
		<li><a href="login.jsp">Login</a></li>
		<li><a href="register.jsp">Register</a></li>

		<c:if test="${UserRole eq 'user'}">
			<li><a href="?page=search">Search</a></li>
			<li><a href="?page=booking">Bookings</a></li>
		</c:if>
		
		<c:if test="${UserRole eq 'manager'}">
            <li><a href="?page=cinemas">Cinemas and Movies</a></li>
        </c:if>
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