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
<body>
<p>${Enchere.pseudo} a remporté l'enchère</p>
<p>Nom de l'article : ${Enchere.nomArticle}</p>
<p>Description : ${Enchere.description}</p>
<p>Meilleure offre : ${Enchere.PrixVente} points par ${DtoEnchereComplete.pseudo}</p>
<p>Mise à prix : ${Enchere.PrixInitial} points</p>
<p>Fin de l'enchère :${Enchere.DateFinEnchere}</p> 


<a href="<%=request.getContextPath()%>/accueil"><button>Retrait effectué</button></a> 


</body>
</html>