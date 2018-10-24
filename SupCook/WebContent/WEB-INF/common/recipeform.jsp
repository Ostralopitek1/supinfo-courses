<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<form method="post" <c:if test="${recipe == null}">action="/auth/recipe/add"</c:if> <c:if test="${recipe != null}">action="/auth/recipe/edit"</c:if>>
    <input type="hidden" name="recipeId" value="${recipe.getId()}">
    <div class="form-group">
        <label for="name">Nom de la recette :</label>
        <input  class="form-control" id="name" placeholder="Nom de votre recette" <c:if test="${recipe != null}"> value="${recipe.getName()}" </c:if> name="name">
    </div>
    <div class="form-group">
        <label for="description">Description :</label>
        <div class="form-group">
            <textarea class="form-control" rows="5" id="description" name = "description">${recipe.getDescription()}</textarea>
        </div>

        <label for="cookingTime">Temps de cuisson (minutes) :</label>
        <fmt:formatNumber var="displayCookieTime" value="${recipe.getCookingTime()/60}" minFractionDigits="0" maxFractionDigits="0" type="number" />
        <input value="${displayCookieTime}"
               type="number" min="0" step="1" class="form-control" id="cookingTime" pattern=""
               placeholder="Temps de cuisson (minutes)"
               name="cookingTime" required>
        <div class="form-group">
            <label for="difficulty">Difficulté :</label>
            <select name="difficulty" class="form-control" id="difficulty">
                <option value="1" <c:if test="${recipe != null}"> <c:if test="${recipe.getDifficulty() == 1}"> selected </c:if> </c:if> >Facile</option>
                <option value="2" <c:if test="${recipe != null}"> <c:if test="${recipe.getDifficulty() == 2}"> selected </c:if> </c:if> >Moyenne</option>
                <option value="3" <c:if test="${recipe != null}"> <c:if test="${recipe.getDifficulty() == 3}"> selected </c:if> </c:if> >Difficile</option>
                <option value="4" <c:if test="${recipe != null}"> <c:if test="${recipe.getDifficulty() == 4}"> selected </c:if> </c:if> >Chef</option>
            </select>
        </div>

        <div class="form-group">
            <label for="preparationTime">Temps de préparation:</label>
            <select name="preparationTime" class="form-control" id="preparationTime">
                <option value="600" <c:if test="${recipe != null}"> <c:if test="${recipe.getPreparationTime() == 600}"> selected </c:if> </c:if> >de 5min à 10min</option>
                <option value="1200" <c:if test="${recipe != null}"> <c:if test="${recipe.getPreparationTime() == 1200}"> selected </c:if> </c:if> >de 10min à 20min</option>
                <option value="2400" <c:if test="${recipe != null}"> <c:if test="${recipe.getPreparationTime() == 2400}"> selected </c:if> </c:if> >de 20min à 40min</option>
                <option value="3600" <c:if test="${recipe != null}"> <c:if test="${recipe.getPreparationTime() == 3600}"> selected </c:if> </c:if> >de 40min à 60min</option>
                <option value="3601" <c:if test="${recipe != null}"> <c:if test="${recipe.getPreparationTime() == 3601}"> selected </c:if> </c:if> >+ de 60min</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="category">Catégorie :</label>
        <select name="category_id" class="form-control" id="category">
            <c:forEach items="${categories}" var="item">
                <option value="${item.getId()}"
                <c:if test="${category.getId() == item.getId()}">
                    selected
                </c:if>
                >${item.getName()}</option>
            </c:forEach>
        </select>
    </div>
    <input type="submit" class="btn btn-primary form-control" value="Enregistrer">
</form>

<p> Aucune catégorie ne correspond à votre recette ? Cliquez sur le bouton ci-dessous pour en ajouter une nouvelle</p>
<a href="/auth/category/add" role="button" class="but_formular btn btn-primary">Ajouter une catégorie</a>
<c:if test="${recipe != null}">
    <table class="table products form-group">
        <thead>
            <tr><th>Images</th><th></th></tr>
        </thead>
        <tbody>
        <c:forEach items="${pictures}" var="picture">
            <tr>
                <td>${picture.getUrl()}</td>
                <td class="btn btn-danger">
                    <a class="deco-none" href="/auth/picture/remove?pictureId=${picture.getId()}&recipeId=${recipe.getId()}">
                        <span class="glyphicon glyphicon-trash"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method="post" action="/auth/picture/add" class="form-inline">
        <input type="hidden" name="recipeId" value="${recipe.getId()}">
        <input class="form-control" type="text" name="pictureUrl" placeholder="http://lien/votre_image.jpg">
        <input type="submit" class="btn btn-primary" value="Ajouter une image">
    </form>
    <br>
    <table class="table products form-group">
        <thead>
        <tr><th>Produits</th><th></th></tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.getName()}</td>
                <td class="btn btn-danger">
                    <a class="deco-none" href="/auth/product/remove?productId=${product.getId()}&recipeId=${recipe.getId()}">
                        <span class="glyphicon glyphicon-trash"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method="post" action="/auth/product/add" class="form-inline">
        <input type="hidden" name="recipeId" value="${recipe.getId()}">
        <input class="form-control" type="text" name="productName" placeholder="Nom du produit">
        <input type="submit" class="btn btn-primary" value="Ajouter un produit">
    </form>
</c:if>