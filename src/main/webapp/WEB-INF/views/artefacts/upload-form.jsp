<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <form  action="/resources/create" method="post"
           enctype="multipart/form-data">
        <div> <input type="file" name="document" required></div>
        <div> Informacje o pliku: (na razie tylko niezbędne)</div>
        <div> <label>Typ: <input type="text" name="type"
                                 placeholder="raport, wykre, czy coś"></label> </div>
        <div><label>Opis: <textarea rows="5" cols="70" name="description"
                       placeholder="Enter text here... "></textarea></label></div>
        <input type="submit" value="Prześlij dane">
    </form>
</div>