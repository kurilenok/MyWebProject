<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
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

<c:url value="/login" var="loginUrl" />

<form name="Form" action="${loginUrl}" method="post">
    <h4>Username:</h4>
    <input name="username" type="text" required="true"/>
    <br/>
    <h4>Password:</h4>
    <input name="password" type="text" required="true"/>
    <!-input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/-->
    <br/>
    <br/>
    <input type="submit" value="Log in">
</form>
</body>
</html>

