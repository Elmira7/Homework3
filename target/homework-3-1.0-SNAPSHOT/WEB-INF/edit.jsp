<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Редактор</title>
    <style><%@include file="edit.css" %></style>

</head>
<body>
    <main>
        <c:if test="${requestScope.name.equals(\"publication\")}">
            <form action="edit" method="post">

                Название <input type="text" name="title" value="${requestScope.object.getTitle()}">

                Автор <select name="author">
                <c:forEach var="user" items="${requestScope.users}">
                    <option value="${user.getId()}">${user.getName()}</option>
                </c:forEach>
            </select>
                <p>Сообщение <textarea type="text" name="body">${requestScope.object.getBody()}</textarea><p>
                <input type="hidden" name="id" value="${requestScope.object.getId()}">
                <button type="submit" name="object" value="publication">Отправить</button>
            </form>
        </c:if>
        <c:if test="${requestScope.name.equals(\"user\")}">
            <form action="edit" method="post">

                Имя <input type="text" name="name" value="${requestScope.object.getName()}">
                Номер телефона <input type="text" name="phone" value="${requestScope.object.getPhone()}">
                Почта <input type="text" name="mail" value="${requestScope.object.getMail()}">

                <input type="hidden" name="id" value="${requestScope.object.getId()}">
                <button type="submit" name="object" value="user">Отправить</button>
            </form>
        </c:if>
    </main>
</body>
</html>
