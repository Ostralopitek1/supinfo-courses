<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>SUPFood</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/SUPFood.css">
</head>
<body>

<%@include file="/WEB-INF/common/header.jsp" %>

<br><br>
<div class="container">
    <%@include file="../common/recipeform.jsp"%>
</div>
<br><br>

<%@include file="/WEB-INF/common/footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
