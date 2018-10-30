<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add a product</title>
</head>
<%@include file="/WEB-INF/template/header.jsp"%>
<body>
    <h1>Add a product</h1>
    <form action="/auth/addProduct" method="post">
        <div>
            <label for="name">Name</label>
            <input type="text" id="name" name="name" placeholder="Name" minlength="3" required>
        </div>
        <div>
            <label for="content">Content</label>
            <input type="text" id="content" name="content" placeholder="Content" minlength="5" required>
        </div>
        <div>
            <label for="price">Price</label>
            <input type="number" step="0.01" id="price" name="price" placeholder="Price" min="0" required>
        </div>
        <select name="categoryId">
            <c:forEach items="${requestScope.categories}" var="cat">
                <option value="${cat.id}">${cat.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Add">
    </form>
</body>
<%@include file="/WEB-INF/template/footer.jsp"%>
</html>
