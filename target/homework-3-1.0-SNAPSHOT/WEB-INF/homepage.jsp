<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Главная</title>
    <style><%@include file="homepage.css" %></style>
</head>
<body>
    <header>
        <form class = "header-form" action="/" method="get">
            <button type="submit" name="object" value="publication">Посты</button>
            <button type="submit" name="object" value="user">Авторы</button>
            <button type="submit" name="object" value="like">Понравившиеся</button>
        </form>
        <form class = "header-form"  action="/remove" method="get">
            <button href="/remove">Удаление</button>
        </form>
    </header>
    <main>
        <c:choose>
            <c:when test="${requestScope.catalog.equals(\"publication\")}">
                <form class = 'filter__f' action="/" method="get">
                    <h2>Фильтр</h2>
                    <div class="filter_block">
                        <div class="filter_block_b">
                    <h3>Автор</h3>
                        <select name="author">
                            <c:forEach var="user" items="${requestScope.users}">
                            <option value="${user.getId()}">${user.getName()}</option>
                            </c:forEach>
                    </select>
                        </div>
                        <div class="filter_block_b">
                        <h3>Дата</h3>
                        <input type="date" name = "date">
                        </div>
                        <button class="button m" type="submit" name="filter" value="publication">Фильтр</button>
                    </div>
                </form>

                <h1 class="text">Публикации</h1>
                <div class="publication">
                    <c:forEach var="publication" items="${requestScope.publications}">
                        <form class="edit" action="edit" method="get">
                            <h3><c:out value="${publication.getTitle()}"/></h3>
                            <h5><c:out value="${publication.getBody()}"/></h5>
                            <h6><c:out value="${publication.getDate()} ${publication.getAuthorName()}"/></h6>
                            <input type="hidden" name="publication" value="${publication.getId()}">
                            <button class="button" type="submit" name="object" value="publication">Редактировать</button>
                        </form>
                    </c:forEach>
                </div>

                <form class = "add" action="add" method="post">
                    <h2 class="text">Добавить пост</h2>
                    Автор <select name="author">
                    <c:forEach var="user" items="${requestScope.users}">
                        <option value="${user.getId()}">${user.getName()}</option>
                    </c:forEach>
                </select>
                    Название <input type="text" name="title">
                    <p>Сообщение <textarea type="text" name="body"></textarea><p>
                    <button class="button" type="submit" name="object" value="publication">Опубликовать</button>
                </form>
            </c:when>


            <c:when test="${requestScope.catalog.equals(\"user\")}">
                <h2>Пользователи</h2>
                <div class="users">
                    <c:forEach var="user" items="${requestScope.users}">
                    <form class="edit" action="edit" method="get">
                        <h3><c:out value="${user.getName()}"/></h3>
                        <h5><c:out value="${user.getPhone()}"/></h5>
                        <h6><c:out value="${user.getMail()}"/></h6>
                        <input type="hidden" name="user" value="${user.getId()}">
                        <button class="button" type="submit" name="object" value="user">Редактировать</button>
                    </form>
                    </c:forEach>
                </div>

                <form class = "add" action="add" method="post">
                    <h3>Добавить автора</h3>
                    <p>Имя <input type="text" name="name"><p>
                    Почта <input type="email" name = "mail">
                    Телефон <input type="tel" name = "phone">
                    <button class="button" type="submit" name="object" value="user">Добавить</button>
                </form>
            </c:when>

            <c:when test="${requestScope.catalog.equals(\"like\")}">
                <h2 class="text">Лайки</h2>
                <div class="likes">
                    <c:forEach var="like" items="${requestScope.likes}">
                    <form class="edit" action="edit" method="get">
                        <h4><c:out value="Пользователю ${like.getNameAuthor()} понравилась запись ${like.getTitlePost()} ${like.getDate().getDate()} числа"/></h4>

                        <input type="hidden" name="author" value="${like.getIdAuthor()}">
                        <input type="hidden" name="post" value="${like.getIdPost()}">
                        <input type="hidden" name="like" value="">
                        <button class="button" type="submit" name="object" value="like">Обновить</button>
                    </form>
                    </c:forEach>
                </div>

                <form class = "add" action="add" method="post">
                    <h2 class="text">Добавить лайк</h2>
                    Пользователь <select name="author">
                    <c:forEach var="user" items="${requestScope.users}">
                        <option value="${user.getId()}">${user.getName()}</option>
                    </c:forEach>
                </select>
                    Автор <select name="post">
                    <c:forEach var="publication" items="${requestScope.publications}">
                    <option value="${publication.getId()}">${publication.getTitle()}</option>
                    </c:forEach>
                    </select>
                    <button class="button" type="submit" name="object" value="like">Опубликовать</button>
                </form>
            </c:when>

        </c:choose>

    </main>
</body>
</html>
