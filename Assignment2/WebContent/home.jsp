<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="edu.unsw.comp9321.model.*"%>    
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
			<c:choose>
				<c:when test="${not empty nowShowingMovies}">
					<c:forEach var="movie" items="${nowShowingMovies}">
						<div class="movie">
							<a href="details.jsp"><img class="mini" alt="poster"
								src="img/movie1.jpg"></a>
							<p>
								<a href="details.jsp">${movie.title}</a>
							</p>
							<div class="rating">
								<c:forEach begin="1" end="${movie.ageRating}">
									☆
								</c:forEach>
							</div>
						</div>				
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p><h1>Sorry, No Showing Movies!</h1></p>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>


	<h2 class="highlight">Coming Soon</h2>
	ORDER BY RELEASE DATE
	<ul class="movieList">
		<li>
			<c:choose>
				<c:when test="${not empty comingSoonMovies}">
					<c:forEach var="movie" items="${comingSoonMovies}">
						<div class="movie">
							<a href=""><img class="mini" alt="poster"
								src="${pageContext.request.contextPath}/img/movie1.jpg"></a>
							<p>
								<a href="">${movie.title}</a>
							</p>
						</div>			
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p><h1>Sorry, No Coming Soon Movies!</h1></p>
				</c:otherwise>
			</c:choose>

		</li>
	</ul>

</body>
</html>