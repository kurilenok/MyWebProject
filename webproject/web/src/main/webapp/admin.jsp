<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<body>
		<h1>Welcome back, God!</h1>
		<h4>Periodicals in database:</h4>
		 <table border="1px">
        	<tr bgcolor="#ccc">
        		<th>Id</th>
        		<th>Title</th>
        		<th>Price</th>
				<th>Image</th>
        		<th></th>
        	</tr>
        	<c:set var="i" scope="page" value="0"/>
        	<c:forEach var="p" items="${requestScope.periodicals}"> 
            	<tr>
            		<c:set var="i" value="${i + 1}" scope="page"/>
            		<td><c:out value="${i}"/>
            		<td><c:out value="${p.getTitle()}"/>  
            		<td><c:out value="${p.getPrice()}"/>
					<td><img src="${p.getLink()}" alt="${p.getTitle()}"></td>
            		<td><a href="/webproject/delete?id=${p.getId()}">Delete</a></td>
        		<tr/>
        	</c:forEach>
        </table>
        
        <h4>Add periodical?</h4>
        <form action="/webproject/add" method="post">
			Title:
			<input name="title" type="text"><br/>
			Price:
			<input name="price" type="text"><br/>
			Link:
			<input name="link" type="text"><br/>
			<input type="submit" value="Add">
		</form>
        
        <br/>
		<br/>
		
		<a href="/webproject/logout">Log out</a>
        
	</body>
</html>
