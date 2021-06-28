<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>

    <form action="/user/register" method="post">
        <div> <label>Login: <input type="text" name="username"></label> </div>
        <div> <label>E-mail: <input type="text" name="email"></label> </div>
        <div> <label>Password: <input type="passwd" name="passwd1"></label> </div>
        <div> <label>Re-type Password: <input type="passwd" name="passwd2"></label> </div>
        <input type="submit" value="Register">
    </form>

</body>
</html>
