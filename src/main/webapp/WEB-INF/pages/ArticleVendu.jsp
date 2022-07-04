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
<p>Pseudo a remporté l'enchere</p>
<p>Nom de l'article : ${DtoEnchereComplete.nomArticle}</p>
<p>Description : ${DtoEnchereComplete.description}</p>
<p>Meilleure offre : ${DtoEnchereComplete.PrixVente}</p>
<p>Mise à prix : ${DtoEnchereComplete.PrixInitial}</p>
<p>Fin de l'enchère :</p> 
<p>Retrait :</p>
<p>Vendeur :</p>


</body>
</html>