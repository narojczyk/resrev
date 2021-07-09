<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Szczegóły zasobu</h2>
<p>ID: ${artefact.uuid}</p>
<p>dodany przez: <c:out value="${userNamesMap[artefact.userUuid]}"/></p>
<p>dodany: ${artefact.created}, zaktualizowany ${artefact.modified}</p>
<p>katekogia: ${artefact.type}</p>
<p>plik: ${artefact.filetype}</p>

<h3>Komentarze</h3>
<c:forEach items="${comments}" var="comment">
    <h6>Dodano: ${comment.created}, przez: ${userNamesMap[comment.commenterUuid]}</h6>
    <p>${comment.commentMessage}</p>
</c:forEach>

<c:choose>
    <c:when  test = "${displayCommentForm == 'displayForm'}">
        <%@ include file="/WEB-INF/views/comments/add.jsp" %>
    </c:when>
    <c:otherwise>
        <a href="/resources/show/?uuid=${artefact.uuid}&cmt=displayForm">
            <button type="button">Dodaj komentarz</button></a>
    </c:otherwise>
</c:choose>

