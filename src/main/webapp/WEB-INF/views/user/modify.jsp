
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div>
    <h2>Modyfikacja danych użytkownika</h2>
    <form action="/user/modify" method="post">
        <div> <label>Login:  <input type="text" name="username" placeholder="${user.username}"></label> </div>
        <div> <label>E-mail: <input type="email" name="email" placeholder="${user.email}"></label> </div>
        <input type="hidden" name="userUuid" value="${user.uuid}">
        <input type="submit" value="Modify">
    </form>
</div>