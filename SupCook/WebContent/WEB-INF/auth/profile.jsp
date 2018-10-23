<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>SUPFood</title>
    <link rel="stylesheet" type="text/css" href="../css/SUPFood.css"/>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>

</head>
<body onload="Highlight();">

<%@include file="/WEB-INF/common/header.jsp" %>

<br><br>
<div class="container text-center"><h1> Vos informations personnelles</h1></div>
<br><br>
<div class="container">
    <form method="post" action="http://localhost:8080/auth/profile">
        <div class="form-group">
            <label for="email">Email :</label>
            <input type="email" class="form-control" id="email" pattern="[^ @]*@[^ @]*" placeholder=${user.getEmail()} name="email">
        </div>
        <div class="form-group">
            <label for="firstname">Prénom :</label>
            <input type="text" class="form-control" id="firstname"  placeholder=${user.getFirstName()} name="firstname">
        </div>
        <div class="form-group">
            <label for="lastname">Nom :</label>
            <input type="text" class="form-control" id="lastname"  placeholder=${user.getLastName()} name="lastname">
        </div>
        <div class="form-group">
            <label for="postalCode">Code postal :</label>
            <input type="text" class="form-control" id="postalCode"  placeholder=${user.getPostalCode()} name="postalCode">
        </div>
        <div class="form-group">
            <label for="pwd">Mot de passe:</label>
            <p>Votre password doit être de minimum 8 caractères</p>
            <input oninput="validate_mdp1(); validate_mdp2()" type="password" class="form-control" id="pwd" placeholder="Nouveau mot de passe" name="pswd1">
            <div id="pass-msg-1" class="alert"></div>
            <input oninput="validate_mdp2()" type="password" class="form-control" id="pwd2" placeholder="Confirmation de mot de passe" name="pswd2">
            <div id="pass-msg-2" class="alert"></div>
        </div>


        <button id="submit" type="submit" class="btn btn-primary">Mettre à jour</button>
    </form>
</div>
<br><br>

<%@include file="/WEB-INF/common/footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/SUPFood.js"></script>


</body>
