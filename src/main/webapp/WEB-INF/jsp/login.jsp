<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="a" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Логін</title>
</head>
<body>

<style>
    html {
        background-color: #121212;
        color: white;
    }
    main {
        border: 2px solid darkgray;
        border-radius: 10px;
        width: 35%;
        margin: 100px auto auto;
    }
    .input, .button {
        display: block;
        margin: 15px auto;
        padding: 7px 10px;
        border-radius: 5px;
    }

    .input {
        width: 55%;
    }

    h2 {
        margin: 20px auto 0;
        text-align: center;
    }
    p {
        margin: 20px auto 25px;
        text-align: center;
        color: red;
    }
</style>

<main>
    <form action="/login" method="post">
        <h2>Логін</h2>
        <input type="text" name="login" class="input" placeholder="somename">
        <input type="password" name="password" class="input" placeholder="password">
        <input type="submit" value="Підтвердити" class="button">

    </form>
    <a:if test="${errorMsg != null}">
        <p><a:out value="${errorMsg}"></a:out></p>
    </a:if>
</main>
</body>
</html>