<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<%@include file="/WEB-INF/template/header.jsp"%>
<body>
    <form action="/login" method="post">
        <label for="username">Username</label>
        <input id="username" name="username" placeholder="Username"/>
        <input type="submit" value="Login"/>
    </form>
</body>
<%@include file="/WEB-INF/template/footer.jsp"%>
</html>