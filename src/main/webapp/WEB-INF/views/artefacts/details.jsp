<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Szczegóły zasobu</h2>
<p>ID: ${artefact.uuid}</p>
<p>dodany przez: <c:out value="${userNamesMap[artefact.userUuid]}"/></p>
<p>dodany: ${artefact.created}, zaktualizowany ${artefact.modified}</p>
<p>katekogia: ${artefact.type}</p>
<p>plik: ${artefact.filetype}</p>
<p>nazwa: ${artefact.fileName}</p>
<p>SHA-1: ${artefact.fileSha1}</p>
<p>Opis zawartości: ${artefact.description}</p>

<h3>Udostępniony</h3>

<c:choose>
    <c:when  test = "${showArtefactSharingForm == 'showArtefactSharingForm'}">
        <%@ include file="/WEB-INF/views/artefacts/share-form.jsp" %>
    </c:when>
    <c:otherwise>
        <a href="/resources/show/?uuid=${artefact.uuid}&insert=showArtefactSharingForm">
            <button type="button">Udostępnij</button></a>
    </c:otherwise>
</c:choose>

<h3>Komentarze</h3>
<c:forEach items="${comments}" var="comment">
    <h6>Dodano: ${comment.created}, przez: ${userNamesMap[comment.commenterUuid]}</h6>
    <p>${comment.commentMessage}</p>
</c:forEach>

<c:choose>
    <c:when  test = "${showCommentForm == 'showCommentForm'}">
        <%@ include file="/WEB-INF/views/comments/add.jsp" %>
    </c:when>
    <c:otherwise>
        <a href="/resources/show/?uuid=${artefact.uuid}&insert=showCommentForm">
            <button type="button">Dodaj komentarz</button></a>
    </c:otherwise>
</c:choose>

