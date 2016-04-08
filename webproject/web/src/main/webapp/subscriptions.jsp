<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
<h4>
    <c:out value="${sessionScope.user.getFirst_name()}"/>
    <c:out value=" "/>
    <c:out value="${sessionScope.user.getLast_name()}"/>
    <c:out value=" "/>
    (<c:out value="${sessionScope.user.getUsername()}"/>), you are currently subscribed to:
</h4>
<table border="1px">
    <tr bgcolor="#ccc">
        <th>Id</th>
        <th>Date</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Title</th>
        <th></th>
    </tr>
    <c:set var="i" scope="page" value="0"/>
    <c:forEach var="s" items="${requestScope.subscriptions}">
        <tr>
                <c:set var="i" value="${i + 1}" scope="page"/>
            <td><c:out value="${i}"/>
            <td><c:out value="${s.getDate()}"/>
            <td><c:out value="${s.getFirst_name()}"/>
            <td><c:out value="${s.getLast_name()}"/>
            <td><c:out value="${s.getTitle()}"/>
            <td><a href="/webproject/unsubscribe?id=${s.getId()}">Unsubscribe</a></td>
        <tr/>
    </c:forEach>
</table>

<h4>Wanna subscribe to more periodicals?</h4>
<table border="1px">
    <tr bgcolor="#ccc">
        <th>Id</th>
        <th>Title</th>
        <th>Price</th>
        <th></th>
    </tr>
    <c:set var="j" scope="page" value="0"/>
    <c:forEach var="p" items="${requestScope.periodicals}">
        <tr>
                <c:set var="j" value="${j + 1}" scope="page"/>
            <td><c:out value="${j}"/>
            <td><c:out value="${p.getTitle()}"/>
            <td><c:out value="${p.getPrice()}"/>
            <td><a href="/webproject/subscribe?id=${p.getId()}">Subscribe</a></td>
        <tr/>
    </c:forEach>
</table>

<br/>
<br/>

<a href="/webproject/logout">Log out</a>

</body>
</html>
