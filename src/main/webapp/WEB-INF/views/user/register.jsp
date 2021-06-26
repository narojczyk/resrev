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
        <div> Login: <input type="text" name="userLogin"> </div>
        <div> E-mail: <input type="text" name="userEmail"> </div>
        <div> Password: <input type="passwd" name="userPasswd"> </div>
        <div> Re-type Password: <input type="passwd" name="userPasswd2"> </div>
        <input type="submit" value="Register">
    </form>

</body>
</html>
