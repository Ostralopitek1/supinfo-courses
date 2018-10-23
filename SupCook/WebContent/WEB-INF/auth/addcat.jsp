<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>SUPFood</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/SUPFood.css">
</head>
<body>

<%@include file="../common/header.jsp" %>
<br><br>
<div class="container">
    <form method="post" action="addcat">
    <div class="form-group">
        <label for="name">Nom:</label>
        <input type="text" class="form-control" id="name" placeholder="Nom de la catégorie" name="name">
    </div>
        <button id="submit" type="submit" class="btn btn-primary">Enregistrer</button>
    </form>
</div>
<br><br>

<%@include file="../common/footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
