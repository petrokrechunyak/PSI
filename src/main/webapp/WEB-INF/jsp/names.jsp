<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="a" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Сторінка з юзерами</title>
</head>
<body>

<style>
    html {
        background-color: #121212;
        color: white;
    }
    main {
        border: 2px solid darkgray;
        border-radius: 5px;
        width: 50%;
        margin: 100px auto auto;
    }

    table {
        width: 90%;
        margin: auto;
    }
    h2 {
        text-align: center;
    }
    .a {
        padding: 15px;
        text-align: center;
        margin-top: 10px;
    }

    a,a:visited {
        color: white;
        border: 1px solid darkgray;
        padding: 10px;
        border-radius: 5px;
        text-decoration: none;
    }

    a:hover {
        background-color: white;
        color: black;
    }
</style>

<main>
    <h2>Таблиця всіх користувачів</h2>
    <table border="1">
        <tr>
            <th>Ім'я</th>
            <th>Логін</th>
        </tr>
        <a:forEach items="${users}" var="user">
            <tr>
                <td>${user.name}</td>
                <td>${user.login}</td>
            </tr>
        </a:forEach>
    </table>
    <div class="a"><a href="/">На головну сторінку</a> <br></div>

    <br>
</main>
</body>
</html>