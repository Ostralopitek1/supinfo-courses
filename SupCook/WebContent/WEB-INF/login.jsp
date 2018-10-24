<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SUPFood</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/SUPFood.css">
    <script src="${pageContext.request.contextPath}/js/SUPFood.js"></script>
</head>
<body onload="Highlight();">

<%@include file="common/header.jsp" %>
<br><br>

<div class="container">
<form class="form_signin" method="post" action="login">
        <h2 class="form-signin-heading">Connectez vous</h2>
        <label for="inputEmail" class="sr-only">Username or email</label>
        <input type="text" name="identifier" id="inputEmail" class="form-control" placeholder="Nom d'utilisateur ou email" required="" autofocus="">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Mot de passe" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
</form>
</div>
<br><br>

<%@include file="common/footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>