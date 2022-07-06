<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Article vendu</title>
	<style><%@include file="/WEB-INF/css/style.css"%></style>
	<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
	<meta charset="UTF-8">
<style>
@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');
</style>
<body class = "bodyArticleVendu">
<p>${DtoEnchereComplete.pseudo} a remporté l'enchère</p>
<p>Nom de l'article : ${DtoEnchereComplete.nomArticle}</p>
<p>Description : ${DtoEnchereComplete.description}</p>
<p>Meilleure offre : ${DtoEnchereComplete.prixVente} pts par ${DtoEnchereComplete.pseudo}</p>
<p>Mise à prix : ${DtoEnchereComplete.prixInitial}</p>
<p>Fin de l'enchère :${DtoEnchereComplete.dateFinEncheres}</p> 
<p>Retrait : ${DtoEnchereComplete.rueRetrait}</p>
<p>${DtoEnchereComplete.codePostalRetrait} ${DtoEnchereComplete.villeRetrait}</p>


<a href="<%=request.getContextPath()%>/accueil"><button class = "button">Retrait effectué</button></a>

</body>
</html>