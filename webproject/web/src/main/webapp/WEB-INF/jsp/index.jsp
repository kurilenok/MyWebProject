<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<body>

	<script type="text/javascript">
		function validateForm() {
			var a = document.forms["Form"]["username"].value;
			var b = document.forms["Form"]["password"].value;
			if (a == "" || b == "") {
				alert("Please fill in all required fields!");
				return false;
			}
		}
	</script>


	    <s:form name="Form" action="/webproject/login" method="post"
				modelAttribute="user" onsubmit="validateForm()">
			<h4>Username:</h4>
			<s:input path="username" name="username" type="text" required="true"/>
			<br/>
			<h4>Password:</h4>
			<s:input path="password" name="password" type="text" required="true"/>
			<br/>
			<br/>
			<input type="submit" value="Log in">
		</s:form>
	</body>
</html>

