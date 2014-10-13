<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="edu.unsw.comp9321.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Success</title>
</head>
<body>
	<%@ include file="/header.html"%>
	<c:choose>
		<c:when test="${empty sessionScope.info}">
			<strong id="info">You shall not pass!</strong>
		</c:when>
		<c:otherwise>
			<strong id="info">${sessionScope.info} operation failed!</strong>
			<p>${sessionScope.moreInfo}</p>
		</c:otherwise>
	</c:choose>
</body>
</html>