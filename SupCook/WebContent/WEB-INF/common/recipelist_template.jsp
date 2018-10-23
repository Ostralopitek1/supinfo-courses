<%@ page import="com.supfood.servlets.ServletUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container-fluid bg-3 text-center">
    <h3>Les dernières recettes</h3><br>
    <div class="row">
        <div class="col-sm-3">
            <div class="list-group">
                <a class="list-group-item <c:if test="${currentCategoryId == -1}">active</c:if>" href="recipes">Toutes les recettes</a>
                <c:forEach items="${categories}" var="category">
                    <a class="list-group-item <c:if test="${currentCategoryId == category.getId()}">active</c:if>" href="recipes?category=${category.getId()}">${category.getName()}</a>
                </c:forEach>
            </div>
        </div>
        <div class="col-sm-9">
            <% int i = 0; %>
            <c:forEach items="${recipes}" var="recipe">
                <% i++;%>
                <c:if test="<%= i == 1 %>">
                    <div class="col-sm-4" id="ref_height">
                </c:if>
                <c:if test="<%= i != 1 %>">
                    <div class="col-sm-4">
                </c:if>
                    <p>${recipe.getName()}</p>
                    <div class="rating">
                        <a id="star5${recipe.getId()}" class="glyphicon glyphicon-star" href="marks?star=5&recipeId=${recipe.getId()}" title="Donner 5 étoiles"></a>
                        <a id="star4${recipe.getId()}" class="glyphicon glyphicon-star" href="marks?star=4&recipeId=${recipe.getId()}" title="Donner 4 étoiles"></a>
                        <a id="star3${recipe.getId()}" class="glyphicon glyphicon-star" href="marks?star=3&recipeId=${recipe.getId()}" title="Donner 3 étoiles"></a>
                        <a id="star2${recipe.getId()}" class="glyphicon glyphicon-star" href="marks?star=2&recipeId=${recipe.getId()}" title="Donner 2 étoiles"></a>
                        <a id="star1${recipe.getId()}" class="glyphicon glyphicon-star" href="marks?star=1&recipeId=${recipe.getId()}" title="Donner 1 étoile"></a>
                    </div>
                    <img onload="Marks(${ServletUtils.getRecipeAvgMark(recipe)},${recipe.getId()})" src="${recipe.getFirstUrlPicture()}" class="img-responsive img-rounded recipe_index_img" style="width:100%;" alt="Image">
                    <form class="btn" method="get" action="recipe">
                        <input name="id" value="${recipe.getId()}" hidden>
                        <button type="submit" class="but_recipe btn btn-primary">Voir</button>
                    </form>
                    <c:if test="${sessionScope.user != null && recipe.getOwner().getId() == sessionScope.user.getId()}">
                        <form method="get" action="auth/recipe/edit" class="btn">
                            <input name="id" value="${recipe.getId()}" hidden>
                            <button type="submit" class="but_recipe btn btn-default">Modifier</button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>

    <ul class="pagination">
        <c:forEach items="${pages}" var="page">
            <li <c:if test="${currentPage == page}">class="active"</c:if>>
                <a href="/recipes?page=${page}">${page}</a></li>
        </c:forEach>
    </ul>
    <br>
</div><br>