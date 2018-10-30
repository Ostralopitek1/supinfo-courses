<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <p>${sessionScope.username}</p>
    <ul>
        <li><a href="/listProduct">Products</a></li>
        <c:if test="${empty sessionScope.username}">
            <li><a href="/login">Login</a></li>
        </c:if>
        <c:if test="${!empty sessionScope.username}">
            <li><a href="/auth/addCategory">Add category</a></li>
            <li><a href="/auth/addProduct">Add product</a></li>
            <li><a href="/logout">Logout</a></li>
        </c:if>
    </ul>
</header>
