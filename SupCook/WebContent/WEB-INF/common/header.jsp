<%@ page import="com.supfood.api.error.ApiError" %>
<%@taglib
        uri="http://java.sun.com/jsp/jstl/core"
        prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header class="fixed-top">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">SUPFood</a>
            </div>
            <ul class="nav navbar-nav">
                <li id="header_home"><a href="">Home</a></li>
                <li id="header_recipes"><a href="/recipes">Recettes</a></li>
                <c:if test="${sessionScope.user == null}">
                    <li id="header_register"><a href="/register">S'inscrire</a></li>
                </c:if>
                    <li id="header_contact"><a href="/contact">Contact</a></li>
                <c:if test="${sessionScope.user != null}">
                    <li id="header_account"><a href="/auth/profile">Mon compte</a></li>
                </c:if>
            </ul>
            <form class="navbar-form navbar-right" action="/recipes" method="get">
                <div class="input-group input-group-sm">
                    <input type="text" class="form-control" name="search" placeholder="Rechercher une recette">
                    <div class="input-group-btn">
                        <button class="btn btn-default btn-sm" type="submit">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.user != null}">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-plus"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/auth/recipe/add">Ajouter une recette</a></li>
                            <li><a href="/auth/category/add">Ajouter une categorie</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/auth/logout">
                            <span class="glyphicon glyphicon-log-out"></span> Déconnexion
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <li id="header_connect"><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Se connecter</a></li>
                </c:if>
            </ul>
        </div>
    </nav>
    <c:if test="${errorMsg != null}">
        <div class="alert alert-danger">
            <strong>Erreur!</strong> ${errorMsg}
        </div>
    </c:if>
</header>
