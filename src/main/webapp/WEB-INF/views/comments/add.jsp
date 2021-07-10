<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <h2>Wpisz komentarz</h2>
    <form action="/comments/add" method="post">
        <div><textarea rows="5" cols="70" name="commentMessage"
                       placeholder="Enter text here... "></textarea></div>
        <input type="hidden" name="artefactUuid" value="${artefact.uuid}">
        <input type="submit" value="Wyślij komentarz">
    </form>
</div>