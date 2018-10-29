<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add category</title>
</head>
<%@include file="/WEB-INF/template/header.jsp"%>
<body>
    <form action="/auth/addCategory" method="post">
        <label for="name">Name</label>
        <input id="name" name="name" placeholder="Name">
        <input type="submit" value="Add">
    </form>
</body>
<%@include file="/WEB-INF/template/footer.jsp"%>
</html>
