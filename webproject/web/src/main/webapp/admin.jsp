<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
<a href="/webproject/logout">Log out</a>
<br/>
<h1>Welcome back, Admin!</h1>

<h4>Add new banknote to database:</h4>

<script type="text/javascript">
    function validateForm()
    {
        var a=document.forms["Form"]["country"].value;
        var b=document.forms["Form"]["title"].value;
        var c=document.forms["Form"]["link"].value;
        if (a=="" || b=="" || c=="")
        {
            alert("Please fill in all required fields!");
            return false;
        }
    }
</script>

<form name="Form" action="/webproject/add" method="post" onsubmit="validateForm()">
    <table border="1px">
        <tr bgcolor="#ccc">
            <th>Country</th>
            <th>Title</th>
            <th>Image link</th>
        </tr>
        <tr>
            <td><input name="country" type="text" size="9"></td>
            <td><input name="title" type="text" size="9"></td>
            <td><input name="link" type="text" size="24"></td>
        </tr>
    </table>
    <input type="submit" value="Add">
</form>

<br/>

<h4>Banknotes in database:</h4>
<table border="1px">
    <tr bgcolor="#ccc">
        <th>Id</th>
        <th>Country</th>
        <th>Title</th>
        <th>Image</th>
        <th></th>
    </tr>
    <c:set var="i" scope="page" value="0"/>
    <c:forEach var="p" items="${requestScope.periodicals}">
        <tr>
                <c:set var="i" value="${i + 1}" scope="page"/>
            <td><c:out value="${i}"/>
            <td><c:out value="${p.getCountry()}"/>
            <td><c:out value="${p.getTitle()}"/>
            <td><img src="${p.getLink()}" alt="${p.getTitle()}" width="250"></td>
            <td><a href="/webproject/delete?id=${p.getId()}">Delete</a></td>
        <tr/>
    </c:forEach>
</table>

<br/>
<br/>

</body>
</html>
