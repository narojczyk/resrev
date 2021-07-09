<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista użytkowników</title>
</head>
<body>
<h1>zrobić z tego panel admina</h1>
    <div><table>
        <tr>
            <th>Id</th>
            <th>Login</th>
            <th>E-mail</th>
            <th>Role</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.uuid}</td>
                <td><c:out value="${userNamesMap[user.uuid]}"/></td>
<%--                <td>${user.username}</td>--%>
                <td>${user.email}</td>
                <td>${user.role}</td>
            </tr>
        </c:forEach>
    </table></div>

    <c:forEach var="usr" items="${userNamesMap}">
        uuid: ${usr.key}  user name: ${usr.value}<br>
    </c:forEach>

<%--    Tylko dla zalogowanego admina--%>
<%--    <div>--%>
<%--        <a href="/user/new">Add new user</a>--%>
<%--    </div>--%>

</body>
</html>