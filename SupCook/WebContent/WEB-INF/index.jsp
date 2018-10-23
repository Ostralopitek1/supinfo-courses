<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="com.supfood.api.client.ClientApi" %>
<%@ page import="com.supfood.entity.ApiResponse" %>
<%@ page import="com.supfood.entity.Recipe" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="com.google.gson.Gson" %>

<%  Gson gson = new Gson();
    ApiResponse resGetAllRecipes = ClientApi.getAllObject(Recipe.API_SUFFIX);
    Type recipesTypeList = new TypeToken<List<Recipe>>(){}.getType();
    List<Recipe> recipes = gson.fromJson(resGetAllRecipes.getContent(), recipesTypeList);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SUPFood</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/SUPFood.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/SUPFood.js"></script>
</head>
<body onload="Highlight();">

<%@include file="common/header.jsp" %>

<div class="jumbotron">
    <div class="container text-center">
        <h1>SUPFood</h1>
        <p>SUPINFO a voulu créer un livre de recettes de cuisine pour les étudiants.
            Il est important de pouvoir gérer votre budget parce que etant qu'étudiants vous avezun petit budget.
            La nourriture est un gros budget pour un étudiant chaque mois !
            Pour préparer une liste de courses, vous devez toujours avoir accès à vos recettes de cuisine pour gérer
            l'achat de nourriture en évitant d'oublier un produit ou d'acheter trop de nourriture.
            Sur cette application, tous les utilisateurs peuvent ajouter des recettes de cuisine avec une liste de produits, les recettes sont rangés par catégories
    </div>
    <section class="row text-center placeholders">
        <div class="col-6 col-sm-3 placeholder">
            <img src="/images/nbuser.jpg" width="200" height="200" class="img-fluid img-circle" alt="Generic placeholder thumbnail">
            <h4>Le Nombre d'utilisateurs</h4>
            <div class="text-muted">${usersCount}</div>
        </div>
        <div class="col-6 col-sm-3 placeholder">
            <img src="/images/cook1.jpg" width="200" height="200" class="img-fluid img-circle" alt="Generic placeholder thumbnail">
            <h4>Le nombre de recettes</h4>
            <span class="text-muted">${recipesCount}</span>
        </div>
        <div class="col-6 col-sm-3 placeholder">
            <img src="/images/etoile-coeur-square-small.png" width="200" height="200" class="img-fluid img-circle" alt="Generic placeholder thumbnail">
            <h4>La note moyenne</h4>
            <span class="text-muted">${avgMarks}</span>
        </div>
        <div class="col-6 col-sm-3 placeholder">
            <img src="/images/epices.jpg" width="200" height="200" class="img-fluid img-circle" alt="Generic placeholder thumbnail">
            <h4>L'ingredient de base</h4>
            <span class="text-muted">${mostUsedProduct}</span>
        </div>
    </section><br>
</div>

<%@include file="common/recipelist_template.jsp"%>

<%@include file="common/footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
    $( window ).resize(img_height());
</script>
</body>
</html>
