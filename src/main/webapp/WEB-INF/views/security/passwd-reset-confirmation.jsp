<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/includes/html/taglibs.jsp" %>

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
    <h2>Reset hasła dla:</h2>
    <h4>${user.username} (${user.email})</h4>
    <form action="/passwd/reset" method="post">
        <div> <label>Password: <input type="password" name="passwd"></label> </div>
        <div> <label>Re-type Password: <input type="password" name="passwdConfirmed"></label> </div>
        <input type="hidden" name="uuid" value="${user.uuid}">
        <input type="submit" value="Resetuj hasło">
    </form>

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

