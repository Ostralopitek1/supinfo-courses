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
        <form method="post" action="register">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" pattern="[^ @]*@[^ @]*" placeholder="Id@supinfo.com" name="email">
            </div>
            <div class="form-group">
                <label for="firstname">Vous :</label>
                <input type="text" class="form-control" id="username"  placeholder="Nom d'utilisateur" name="username" required>
                <input type="text" class="form-control" id="firstname"  placeholder="Nom" name="firstname" required>
                <input type="text" class="form-control" id="lastname"  placeholder="Prénom" name="lastname" required>
                <input type="text" class="form-control" id="postalCode"  placeholder="Code postal" name="postalCode" required>
            </div>
            <div class="form-group">
                <label for="pwd">Mot de passe:</label>
                <p>Votre password doit être de minimum 8 caractères</p>
                <input oninput="validate_mdp1(); validate_mdp2()" type="password" class="form-control" id="pwd" placeholder="Mot de passe" name="pswd1">
                <div id="pass-msg-1" class="alert"></div>
                <input oninput="validate_mdp2()" type="password" class="form-control" id="pwd2" placeholder="Confirmation de mot de passe" name="pswd2">
                <div id="pass-msg-2" class="alert"></div>
            </div>
            <button id="submit" type="submit" class="btn btn-primary" disabled>Soumettre</button>
        </form>
    </div>
<br><br>

<%@include file="common/footer.jsp" %>

        <script src="js/SUPFood.js"></script>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>