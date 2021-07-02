<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista zasobów użytownika</title>
</head>
<body>

<security:authorize access="isAuthenticated()">
    authenticated as <security:authentication property="principal.username" />
</security:authorize>

<div><table>
    <tr>
        <th>UUID</th>
        <th>user UUID</th>
        <th>type</th>
        <th>file</th>
    </tr>
    <c:forEach items="${artefacts}" var="art">
        <tr>
            <td>${art.uuid}</td>
            <td>${art.userUuid}</td>
            <td>${art.type}</td>
            <td>${art.filetype}</td>
        </tr>
    </c:forEach>
</table></div>
<h2>zasoby własne</h2>
<h2>zasoby udostępnione</h2>



</body>
</html>