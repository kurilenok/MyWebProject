<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<body>
	    <form action="/webproject/login" method="post">
			<h4>Username:</h4>
			<input name="username" type="text"><br/>
			<h4>Password:</h4>
			<input name="password" type="text"><br/>
			<br/>
			<input type="submit" value="Log in">
		</form>
	</body>
</html>
