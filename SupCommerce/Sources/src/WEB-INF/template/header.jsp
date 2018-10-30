<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String username = (String) session.getAttribute("username"); %>
<header>
    <p><%= username == null ? "" : username %></p>
    <ul>
        <li><a href="/listProduct">Products</a></li>
        <% if (username == null) { %>
        <li><a href="/login">Login</a></li>
        <% } else { %>
        <li><a href="/auth/addProduct">Add product</a></li>
        <li><a href="/logout">Logout</a></li>
        <% } %>
    </ul>
</header>
