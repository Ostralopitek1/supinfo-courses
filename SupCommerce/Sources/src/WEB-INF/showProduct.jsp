<html>
<head>
    <title>${requestScope.product.name}</title>
</head>
<%@include file="/WEB-INF/template/header.jsp"%>
<body>
    <h1>${requestScope.product.name}</h1>
    <div>
        <p>Content: ${requestScope.product.content}</p>
        <p>Price: $${requestScope.product.price}</p>
    </div>
</body>
<%@include file="/WEB-INF/template/footer.jsp"%>
</html>
