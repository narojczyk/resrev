
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/includes/html/taglibs.jsp" %>

<html lang="pl">
<%--    <head> tag contents </head>--%>
<%@ include file="/WEB-INF/includes/html/head.jsp" %>

<body>
<%@ include file="/WEB-INF/includes/html/header.jsp" %>

<div>
    <%--Servlet content--%>
    <security:authorize access="hasRole('USER')">
        <c:if test = "${getResource == 'default'}">
            <%@ include file="/WEB-INF/views/user/default.jsp" %>
        </c:if>

        <c:if test = "${getResource == 'info'}">
            <%@ include file="/WEB-INF/views/user/info.jsp" %>
        </c:if>

        <c:if test = "${getResource == 'myartefacts'}">
            <%@ include file="/WEB-INF/views/artefacts/list.jsp" %>
        </c:if>
    </security:authorize>

</div>

<%--<footer> tag contents </footer>--%>
<%@ include file="/WEB-INF/includes/html/footer.jsp" %>


</body>
</html>