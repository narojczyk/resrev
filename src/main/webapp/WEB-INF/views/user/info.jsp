<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div>
    <h2>Panel użytkownika</h2>
    opcje:
        <a href="/user/modify?uuid=${user.uuid}">modyfikuj</a>
        <a href="/passwd/reset?uuid=${user.uuid}">reset hasła</a>
    <p>Login: ${user.username}</p>
    <p>ID: ${user.uuid}</p>
    <p>email: ${user.email}</p>
    <p>status: ${user.role}</p>
</div>