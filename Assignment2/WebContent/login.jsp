<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Log in Popcorn Troll</title>

</head>
<body>
    <%@ include file="header.html"%>
    <strong id="info"></strong>
    
    <form action="test" method="post">
        <p>
            Username: <input type="text" name="username" pattern="[a-z]...*" required="required">
        </p>
        <p>
            Password: <input type="password" name="password" id="p" required="required">
        </p>
        <p>
            <input type="submit" value="Log in">
        </p>
    </form>
</body>
</html>