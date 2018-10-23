<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>SUPFood</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/SUPFood.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css"
          integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="
          crossorigin=""/>
    <script src="${pageContext.request.contextPath}/js/SUPFood.js"></script>
</head>
<body onload="Highlight();">

<%@include file="/WEB-INF/common/header.jsp" %>
<br><br>
    <div class=container>
        <h2> Qui sommes nous ?</h2>
        <p>Un groupe d'étudiants motivés et passionnés d'informatique ! Le projet de SUPFood a été lancé suite à un repas qui a mal tourné. Marre d'aller au Fastfood nous avons décidés de développer une plateforme de partage de recettes et d'astuces culinaires en ligne.</p>
        <br>
        <h2> Où nous trouver ?</h2>
        <div id="mapid"></div>
        <br>
        <h2>L'équipe en détails</h2>
        <h4>Adrien BEHADIR</h4>
        <p>Id: 171994</p>
        <h4>Valentin LEROY</h4>
        <p>Id: 213639</p>
        <h4>Benjamin MATHIAS</h4>
        <p>Id: 218295</p>
        <h4>Romain PERRIN</h4>
        <p>Id: 220921</p>
    </div>
<br><br>
<%@include file="common/footer.jsp" %>

    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js"
        integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw=="
        crossorigin=""></script>
    <script>
        var mymap = L.map('mapid').setView([48, 2], 8);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(mymap);
    </script>
    </body>
</html>