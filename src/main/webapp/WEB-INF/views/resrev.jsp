<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/includes/html/taglibs.jsp" %>
<%--<%@ include file="/footer.jsp" %>--%>

<html lang="pl">
<%--    <head> tag contents </head>--%>
    <%@ include file="/WEB-INF/includes/html/head.jsp" %>

<body>
    <%--<headder> tag contents </headder>--%>
    <security:authorize access="isAuthenticated()">
        <%@ include file="/WEB-INF/includes/html/header.jsp" %>
    </security:authorize>

    <security:authorize access="isAnonymous()">
        <%@ include file="/includes/html/anonymous/header.jsp" %>
    </security:authorize>

    <div>
        <%--Servlet content--%>
        <security:authorize access="hasRole('USER')">
            <%@ include file="/WEB-INF/views/user/dashboard.jsp" %>
        </security:authorize>

        <security:authorize access="hasRole('ADMIN')">
            <%@ include file="/WEB-INF/views/admin/dashboard.jsp" %>
        </security:authorize>

        <security:authorize access="isAnonymous()">
            <%@ include file="/includes/html/anonymous/dashboard.jsp" %>
        </security:authorize>

    </div>

    <%--<footer> tag contents </footer>--%>
    <security:authorize access="isAuthenticated()">
        <%@ include file="/WEB-INF/includes/html/footer.jsp" %>
    </security:authorize>

    <security:authorize access="isAnonymous()">
        <%@ include file="/includes/html/anonymous/footer.jsp" %>
    </security:authorize>

</body>
</html>
