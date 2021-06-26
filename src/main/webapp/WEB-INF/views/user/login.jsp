<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User login</title>
</head>
<body>

    <form action="/user/login" method="post">
        <div> Login: <input type="text" name="userLogin"> </div>
        <div> Password: <input type="passwd" name="userPasswd"> </div>
        <input type="submit" value="Login">
    </form>

</body>
</html>
