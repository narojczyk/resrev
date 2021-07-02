<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="header-palette-authorised">
    <div class="nav-bar">
        <div class="nav-info">
            <security:authentication property="principal.username" />
        </div>
        <div class="nav-item"><a class="nav-link" href="/logout">Wyloguj</a></div>
    </div>
</div>

<div class="header-palette-authorised">
    <div class="nav-bar">
        <div class="nav-info">Menu:</div>
        <div class="nav-item"><a class="nav-link" href="/resources/userartefacts">Lista zasobów</a></div>
        <div class="nav-item"><a class="nav-link" href="/">Home</a></div>
    </div>
</div>