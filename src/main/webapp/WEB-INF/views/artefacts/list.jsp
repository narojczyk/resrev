
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>zasoby własne</h2>
<div><table>
    <tr>
        <th>artefakt UUID</th>
        <th>type</th>
        <th>file</th>
        <th>opcje</th>
    </tr>
    <c:forEach items="${artefacts}" var="art">
        <tr>
            <td>${art.uuid}</td>
            <td>${art.type}</td>
            <td>${art.filetype}</td>
            <td><a href="/resources/show?uuid=${art.uuid}">szczegóły</a></td>
        </tr>
    </c:forEach>
</table></div>

<h2>zasoby udostępnione</h2>

<div><table>
    <tr>
        <th>artefakt UUID</th>
        <th>owner</th>
        <th>type</th>
        <th>file</th>
    </tr>
    <c:forEach items="${sharedArtefacts}" var="art">
        <tr>
            <td>${art.uuid}</td>
            <td><c:out value="${userNamesMap[art.userUuid]}"/></td>
            <td>${art.type}</td>
            <td>${art.filetype}</td>
            <td><a href="/resources/show?uuid=${art.uuid}">szczegóły</a></td>
        </tr>
    </c:forEach>
</table></div>