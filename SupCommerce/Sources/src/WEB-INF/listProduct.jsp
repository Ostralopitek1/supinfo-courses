<%@ page import="com.supinfo.sun.supcommerce.bo.SupProduct" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Products list</title>
</head>
<%@include file="/WEB-INF/template/header.jsp"%>
<body>
    <h1>Products list</h1>
    <div>
        <% List<SupProduct> products = (List<SupProduct>) request.getAttribute("products"); %>
        <% for (SupProduct product : products) { %>
        <div>
            <a href="/showProduct?id=<%= product.getId() %>">
                <%= product.getName() + " - $" + product.getPrice()%>
            </a>
        </div>
        <% } %>
    </div>
</body>
<%@include file="/WEB-INF/template/footer.jsp"%>
</html>
