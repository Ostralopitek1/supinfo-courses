<%@ page import="com.supinfo.sun.supcommerce.bo.SupProduct" %>
<% SupProduct product = (SupProduct) request.getAttribute("product"); %>

<html>
<head>
    <title><%= product.getName() %></title>
</head>
<%@include file="/WEB-INF/template/header.jsp"%>
<body>
    <h1><%= product.getName() %></h1>
    <div>
        <p>Content: <%= product.getContent() %></p>
        <p>Price: $<%= product.getPrice() %></p>
    </div>
</body>
<%@include file="/WEB-INF/template/footer.jsp"%>
</html>
