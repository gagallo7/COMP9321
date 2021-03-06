<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="edu.unsw.comp9321.model.*"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Searching for Movies</title>
</head>
<body>
	<%@ include file="/header.html"%>
	
		<h2>Showing ${fn:length(movies)} results</h2>
		<c:forEach var="movie" items="${movies}">
			<div class="result">
				<div class="movie2">
					<a href="?action=detailMovie&id=${movie.id}"><img class="mini" alt="poster" src="img/${movie.urlPost}"></a>
					<br> <a href="?action=detailMovie&id=${movie.id}">${movie.title}</a>
					<div class="rating2">
						<c:forEach begin="1" end="${movie.rating}">
							☆
						</c:forEach>
					</div>
				</div>
		
				<div class="movieInfo">
					<p>
						<strong>Genre:</strong> ${movie.genre}
					</p>
					<p>
						<strong>Actors:</strong> ${movie.actors}
					</p>
					<p>
						<a href="?action=detailMovie&id=${movie.id}">More Details</a>
					</p>
				</div>
			</div>
		</c:forEach>

</body>
</html>
