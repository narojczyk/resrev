<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista użytkowników</title>
</head>
<body>
    <div><table>
        <tr>
            <th>Id</th>
            <th>Login</th>
            <th>E-mail</th>
            <th>Access level</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.uuid}</td>
                <td>${user.login}</td>
                <td>${user.email}</td>
                <td>${user.accessLevel}</td>
            </tr>
        </c:forEach>
    </table></div>

<%--    Tylko dla zalogowanego admina--%>
<%--    <div>--%>
<%--        <a href="/user/new">Add new user</a>--%>
<%--    </div>--%>

</body>
</html>