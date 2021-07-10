<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
<%--    <c:set var = "current"--%>
<%--           value = "<security:authentication property=\"principal.username\" />" />--%>

    <form  action="/share/artefact" method="post">
        <select name="userToShareWith">
            <option disabled selected value>-select-</option>
            <c:forEach items="${userNamesMap}" var="user" >
<%--                <c:if test = "${user.value != current}">--%>
                    <option value="${user.value}">${user.value}</option></p>
<%--                </c:if>--%>
            </c:forEach>
        </select>

        <input type="hidden" name="artefactUuid" value="${artefact.uuid}">
        <input type="submit" value="Udostępnij">
    </form>
</div>