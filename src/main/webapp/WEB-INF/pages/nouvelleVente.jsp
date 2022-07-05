<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
</head>
<body>
	<form action="<%=request.getContextPath()%>/nouvelleVente" method="post">

	
	<h1>Nouvelle vente</h1>
	
<img src="${image}" alt="${detailImageVente}"/>

	<div>
		<label for="article">Article :</label> 
		<input type="text" id="article" name="article" class="form-control" value="" required  maxlength="30"> 
		
		
		<label for="article">Description :</label> 
		<textarea id="story" name="description" rows="5" cols="33" maxlength="300" value="description">description du produit </textarea>
	</div>
	<div>
	<label for="name">Catégorie : </label>
		<select name="Categorie" id="categ-select">
			<option value="0">--Choisissez une catégorie--</option>
		<c:forEach items="${ListCategorie}" var="categ">
		    <option value="${categ.noCategorie}" id = "Categorie">${categ.libelle}</option>
		</c:forEach>
	</select>
	<div>
		<label for="photoArticle">Photo de l'article</label>
		<input type="submit" name="uploader" value="uploader">
	</div>
	<div>
		<label for="miseaPrix">Mise à prix :</label>
		<input type="number" name="miseaPrix" step="1" value="0" min="0" max="10000">
	</div>
	
	<div>
		<label for="debutEnchere">Debut de l'enchére :</label>
		<input type="date" name="debutEnchére" min="${dateLocal}">
		
		
	</div>
	
	<div>
		<label for="finEnchere">Fin de l'enchére :</label>
		<input type="date" name="finEnchere">
	</div>
	<div>
		<div >
			<label	for="rue">Rue :</label> 
			<input type="text" id="rue" name="rue" class="form-control" value="${rue}" required minlenght="1">
		</div>
		<div>
			<label for="codePostal">Code Postal :</label> 
			<input type="text" id="codePostal" name="codePostal" class="form-control" value="${codePostal}" required minlenght="1">
		</div>
		<div>
			 <label	for="ville">Ville :</label> 
			 <input type="text" id="ville" name="ville" class="form-control" value="${ville}" required minlenght="1"><br />
		</div>
	</div>
	
	<div>
		<input type="submit" name="actionUtilisateur" value="Enregister">
		<input type="submit" name="actionUtilisateur" value="annuler">
		
	</div>
	
	
	
	
</form>

</body>
</html>