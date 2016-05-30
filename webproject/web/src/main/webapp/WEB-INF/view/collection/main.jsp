<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns="http://www.w3.org/1999/xhtml" version="2.0">


    <a href="/webproject/logout">Log out</a>
    <br/>

    <h4>
        <c:out value="${sessionScope.user.getFirst_name()}"/>
        <c:out value=" "/>
        <c:out value="${sessionScope.user.getLast_name()}"/>
        <c:out value=" "/>
        (<c:out value="${sessionScope.user.getUsername()}"/>), here is your Collection:
    </h4>
    <table border="1px">
        <tr bgcolor="#ccc">
            <th>Id</th>
            <th>Title</th>
            <th>Country</th>
            <th>Image</th>
            <th></th>
        </tr>
        <c:set var="i" scope="page" value="0"/>
        <c:forEach var="s" items="${requestScope.collection}">
            <tr/>
            <c:set var="i" value="${i + 1}" scope="page"/>
            <td/>
            <c:out value="${i}"/>
            <td/>
            <c:out value="${s.getNominal()}"/>&nbsp;<c:out value="${s.getTitle()}"/>
            <td/>
            <c:out value="${s.getCountry()}"/>
            <td><img src="${s.getLink()}" alt="Image for ${s.getTitle()}" width="250"></td>
            <td><a href="/webproject/uncollect?id=${s.getId()}">Delete</a></td>
            <tr/>
        </c:forEach>
    </table>

    <h4>Fill free to add these banknotes to your Collection:</h4>

    -&nbsp;
    <c:forEach begin="1" end="${requestScope.maxPages}" varStatus="p">
        <c:choose>
            <c:when test="${p.index == requestScope.currentPage}">
                <b>Page ${p.index}</b>&nbsp;&nbsp;-&nbsp;
            </c:when>
            <c:otherwise>
                <a href="/webproject/collection?page=${p.index}">Page ${p.index}</a>&nbsp;&nbsp;-&nbsp;
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <br/>

    <table border="1px">
        <tr bgcolor="#ccc">
            <th>Id</th>
            <th>Title</th>
            <th>Country</th>
            <th>Image</th>
            <th></th>
        </tr>
        <c:set var="j" scope="page" value="0"/>
        <c:forEach var="b" items="${requestScope.banknotes}">
            <tr/>
            <c:set var="j" value="${j + 1}" scope="page"/>
            <td/>
            <c:out value="${j}"/>
            <td/>
            <c:out value="${b.getNominal()}"/>&nbsp;<c:out value="${b.getTitle()}"/>
            <td/>
            <c:out value="${b.getCountry()}"/>
            <td><img src="${b.getLink()}" alt="Image for ${b.getTitle()}" width="250"></td>
            <td><a href="/webproject/collect?id=${b.getId()}">Add</a></td>
            <tr/>
        </c:forEach>
    </table>

    -&nbsp;
    <c:forEach begin="1" end="${requestScope.maxPages}" varStatus="p">
        <c:choose>
            <c:when test="${p.index == requestScope.currentPage}">
                <b>Page ${p.index}</b>&nbsp;&nbsp;-&nbsp;
            </c:when>
            <c:otherwise>
                <a href="/webproject/collection?page=${p.index}">Page ${p.index}</a>&nbsp;&nbsp;-&nbsp;
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <br/>
    <br/>

</jsp>