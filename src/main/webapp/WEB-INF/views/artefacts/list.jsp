
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>zasoby własne</h2>
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

<h2>zasoby udostępnione</h2>