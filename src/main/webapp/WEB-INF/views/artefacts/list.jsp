<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista zasobów użytownika</title>
</head>
<body>
<div><table>
    <h2>zasoby własne</h2>
    <h2>zasoby udostępnione</h2>
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

</body>
</html>