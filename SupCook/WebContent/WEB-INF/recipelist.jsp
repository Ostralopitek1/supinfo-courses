<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.supfood.servlets.ServletUtils" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>SUPFood</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/SUPFood.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/SUPFood.js"></script>
</head>
    <body onload="Highlight();">
<%@include file="common/header.jsp" %>
<br><br>
<c:if test="${sessionScope.user != null}">
    <div class="col-sm-3">
        <img src="/images/etoile-coeur-square-small.png" onload="Marks(${ServletUtils.getRecipeUserAvgMark(sessionScope.user)},'_all')" hidden>
        <p class="text-right"> La note moyenne de vos recette est de </p>
        <div class="rating">
            <a id="star5_all" class="glyphicon glyphicon-star"></a>
            <a id="star4_all" class="glyphicon glyphicon-star"></a>
            <a id="star3_all" class="glyphicon glyphicon-star"></a>
            <a id="star2_all" class="glyphicon glyphicon-star"></a>
            <a id="star1_all" class="glyphicon glyphicon-star"></a>
        </div>
    </div>
    <br>
    <a href="/auth/recipe/add" role="button" class="but_formular btn btn-primary">Ajouter une recette</a>
    <a href="/auth/category/add" role="button" class="but_formular btn btn-primary">Ajouter une catégorie</a>
</c:if>
<%@include file="common/recipelist_template.jsp"%>
<br><br>
<%@include file="common/footer.jsp" %>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/SUPFood.js"></script>
        <script>
            $( window ).resize(img_height());
        </script>
    </body>
</html>
