<%@ page import="com.supfood.entity.Recipe" %>
<%@ page import="com.supfood.entity.Category" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Recipe recipe = (Recipe)request.getAttribute("recipe");
    Category category = (Category)request.getAttribute("category");
    Integer difficulty = recipe.getDifficulty();
%>
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

<div class="container-fluid bg-3 text-center">
    <div class="row">
        <div class="col-sm-4">
            <h3><%= recipe.getName() %></h3>
            <div class="rating">
                <a class="glyphicon glyphicon-star" href="#5" title="Donner 5 étoiles"></a>
                <a class="glyphicon glyphicon-star" href="#4" title="Donner 4 étoiles"></a>
                <a class="glyphicon glyphicon-star" href="#3" title="Donner 3 étoiles"></a>
                <a class="glyphicon glyphicon-star" href="#2" title="Donner 2 étoiles"></a>
                <a class="glyphicon glyphicon-star" href="#1" title="Donner 1 étoile"></a>
            </div>
            <img src="${recipe.getFirstUrlPicture()}" class="img-responsive img-rounded" style="width:100%" alt="Image">
        </div>
        <div class="col-sm-4" style="margin-top: 20px;">
            <h4>Temps de préparation : <%= recipe.getPreparationTime()/60 %> min</h4>
            <br>
            <h4>Temps de cuisson : <%= recipe.getCookingTime()/60 %> min</h4>
            <br>
            <h4>Catégorie : <%= category.getName() %></h4>
            <br>
            <h4>Description : </h4>
            <pre class="no-style-pre"><%= recipe.getDescription() %></pre>
        </div>
        <br>
        <div class="col-sm-4">
            <h4> Difficulté : </h4>
            <c:forEach var="i" begin="0" end="<%=difficulty%>" step="1">
                <span class="glyphicon glyphicon-star"></span>
            </c:forEach>
            <br>
            <c:if test="${sessionScope.user != null}">
                <button type="button" class="btn btn-danger btn-md" data-toggle="modal" data-target="#Supp_recipe">Supprimer</button>
            </c:if>
        </div>
    </div>
</div>

<br>

<c:if test="${sessionScope.user != null && recipe.getOwner().getId() == sessionScope.user.getId()}">
<div class="modal fade" id="Supp_recipe" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Supprimer ?</h4>
                <h5 class="modal-title"><%= recipe.getName() %></h5>
            </div>
            <div class="modal-body">
                <p>Etes-vous sûr de vouloir supprimer cette recette ?</p>
                <p>Cette opération est irréversible.</p>
            </div>
            <div class="modal-footer">
                <div class="btn-group">
                    <form method="post" style="display: inline;" action="/auth/recipe/remove">
                        <input type="hidden" name="recipeId" value="${recipe.getId()}">
                        <input type="submit" class="btn btn-danger" value="Oui">
                    </form>
                    <button type="button" class="btn btn-success" data-dismiss="modal">Non</button>
                </div>
            </div>
        </div>
    </div>
</div>
</c:if>
</div>
<br><br>

<%@include file="common/footer.jsp" %>

    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
