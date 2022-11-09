<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Удаление</title>
    <style><%@include file="remove.css" %></style>

</head>
<body>
    <main>
        <h2>Удаление постов</h2>
            <form class="remove-form" action="remove" method="post">
                <c:forEach var="publication" items="${requestScope.publications}">
                        <h4>
                            <input id = "post" type="checkbox" name="id" value="${publication.getId()}">
                            <label for="post"><c:out value="${publication.getTitle()} ${publication.getDate()}"/></label>
                        </h4>

                </c:forEach>
                <button type="submit" name="object" value="publication">Удалить посты</button>
            </form>
        <h2>Удаление авторов</h2>
            <form class="remove-form" action="remove" method="post">
                <c:forEach var="user" items="${requestScope.users}">
                    <h4>
                        <input id = "user" type="checkbox" name="id" value="${user.getId()}">
                        <label for="user"><c:out value="${user.getName()}"/></label>
                    </h4>
                </c:forEach>
                <button type="submit" name="object" value="user">Удалить авторов</button>
            </form>
    </main>
</body>
</html>
