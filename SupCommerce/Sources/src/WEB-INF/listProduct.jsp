<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Products list</title>
</head>
<%@include file="/WEB-INF/template/header.jsp"%>
<body>
    <h1>Products list</h1>
    <div>
        <c:forEach items="${requestScope.products}" var="product">
            <div>
                <a href="showProduct?id=${product.id}">${product.name} - $${product.price}</a>
                <form action="auth/removeProduct?id=${product.id}" method="post" style="display: inline; margin-left: 10px;">
                    <input type="submit" value="Remove">
                </form>
            </div>
        </c:forEach>
    </div>
</body>
<%@include file="/WEB-INF/template/footer.jsp"%>
</html>
