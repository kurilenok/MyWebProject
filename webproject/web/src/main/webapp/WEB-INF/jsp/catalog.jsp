<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
<a href="/webproject/logout">Log out</a>
<br/>
<h1>Welcome back, Admin!</h1>

<h4>Add new banknote to Catalog:</h4>

<script type="text/javascript">
    function validateForm() {
        var a = document.forms["Form"]["country"].value;
        var b = document.forms["Form"]["title"].value;
        var c = document.forms["Form"]["link"].value;
        var d = document.forms["Form"]["nominal"].value;
        if (a == "" || b == "" || c == "" || d == "") {
            alert("Please fill in all required fields!");
            return false;
        }
    }
</script>

<form name="Form" action="/webproject/add" method="post" onsubmit="validateForm()">
    <table border="1px">
        <tr bgcolor="#ccc">
            <th>Country</th>
            <th>Nominal</th>
            <th>Currency</th>
            <th>Image link</th>
        </tr>
        <tr>
            <td><input name="country" type="text" size="9"></td>
            <td><input name="nominal" type="text" size="3"></td>
            <td><input name="title" type="text" size="3"></td>
            <td><input name="link" type="text" size="21"></td>
        </tr>
    </table>
    <input type="submit" value="Add">
</form>

<br/>

<h4>Banknotes in Catalog:</h4>

<br/>
-&nbsp;
<c:forEach begin="1" end="${requestScope.maxPages}" varStatus="i">
    <c:choose>
        <c:when test="${i.index == requestScope.currentPage}">
            <b>Page ${i.index}</b>&nbsp;&nbsp;-&nbsp;
        </c:when>
        <c:otherwise>
            <a href="/webproject/index?page=${i.index}">Page ${i.index}</a>&nbsp;&nbsp;-&nbsp;
        </c:otherwise>
    </c:choose>
</c:forEach>

<br/>

<table border="1px">
    <tr bgcolor="#ccc">
        <th>Id</th>
        <th>Country</th>
        <th>Title</th>
        <th>Image</th>
        <th></th>
    </tr>
    <c:set var="i" scope="page" value="0"/>
    <c:forEach var="b" items="${requestScope.banknotes}">
        <tr>
                <c:set var="i" value="${i + 1}" scope="page"/>
            <td><c:out value="${10*(requestScope.currentPage - 1) + i}"/>
            <td><c:out value="${b.getCountry()}"/>
            <td><c:out value="${b.getNominal()}"/>&nbsp;<c:out value="${b.getTitle()}"/>
            <td><img src="${b.getLink()}" alt="Image for ${b.getTitle()}" width="250"></td>
            <td><a href="/webproject/delete?id=${b.getId()}">Delete</a></td>
        <tr/>
    </c:forEach>
</table>

-&nbsp;
<c:forEach begin="1" end="${requestScope.maxPages}" varStatus="i">
    <c:choose>
        <c:when test="${i.index == requestScope.currentPage}">
            <b>Page ${i.index}</b>&nbsp;&nbsp;-&nbsp;
        </c:when>
        <c:otherwise>
            <a href="/webproject/index?page=${i.index}">Page ${i.index}</a>&nbsp;&nbsp;-&nbsp;
        </c:otherwise>
    </c:choose>
</c:forEach>


<br/>
<br/>

</body>
</html>
