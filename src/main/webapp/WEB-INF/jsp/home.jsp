<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="a" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>*Сторінка привітання*</title>
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
        text-align: center;
        padding-bottom: 30px;
    }

    .a {
        padding: 15px;
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
    <h1>Привіт! <a:out value="${user}"></a:out></h1>
    <div class="a"><a href="/names">На сторінку всіх користувачів</a> <br></div>
    <form action="/logout" method="post" id="form">
        <div class="a"><a href="#" onclick="submit()">Вийти</a><br></div>
    </form>
</main>

<script>
    var form = document.getElementById("form");

    function submit () {
        form.submit();
    }
</script>
</body>
</html>