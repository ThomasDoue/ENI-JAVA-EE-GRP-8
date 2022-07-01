<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Accueil</title>
	<style><%@include file="/WEB-INF/css/style.css"%></style>
	<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
<style>
@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');
</style>

<body>
<h1 class="titre">Liste des enchères</h1>
<div class="flex">
<form action="<%=request.getContextPath()%>/accueil" method="post">
	<label for="name">FILTRES :</label>
	<br/>
	<input type="text" id="NomArticle" name="NomArticle" placeholder="Le nom de l'article contient" maxlength="30">
	<br/>
<label for="name">Catégorie : </label>
<select name="Categorie" id="categ-select">
<option value="0">--Choisissez une catégorie--</option>
<c:forEach items="${ListCategorie}" var="categ">
    <option value="${categ.noCategorie}" id = "Categorie">${categ.libelle}</option>
</c:forEach>
</select>
</form>
<button class = "button">Rechercher</button>
</div>



<c:forEach items="${ListEncheres}" var="Encheres">
    <label>${Encheres.nomArticle}</label>
    	<br/>
    <label>Prix : ${Encheres.prixVente}</label>
    	<br/>
    <label>Fin de l'enchère ${Encheres.dateFinEncheres}</label>
    	<br/>
    <label>Vendeur : ${Encheres.pseudo}</label>
    	<br/>
    	<br/>
    	<br/>
</c:forEach>
</body>
	</body>
</html>