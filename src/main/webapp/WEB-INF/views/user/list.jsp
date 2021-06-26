<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista książek</title>
</head>
<body>
<div><a href="">Dodaj książkę</a></div>
<div>
    <table>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Rating</th>
            <th>Description</th>
            <th>Publisher</th>
            <th>Action</th>
        </tr>
<%--        <c:forEach items="${books}" var="book">--%>
<%--            <tr>--%>
<%--                <td>${book.id}</td>--%>
<%--                <td>${book.title}</td>--%>
<%--                <td>${book.rating}</td>--%>
<%--                <td>${book.description}</td>--%>
<%--                <td>${book.publisher.name}</td>--%>
<%--&lt;%&ndash;                <td><a href=""></a></td>&ndash;%&gt;--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
    </table>
</div>

</body>
</html>