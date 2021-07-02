<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Szczegóły zasobu</h2>
<p>ID: ${artefact.uuid}</p>
<p>dodany przez: <c:out value="${userNamesMap[artefact.userUuid]}"/></p>
<p>dodany: ${artefact.created}, zaktualizowany ${artefact.modified}</p>
<p>katekogia: ${artefact.type}</p>
<p>plik: ${artefact.filetype}</p>
        