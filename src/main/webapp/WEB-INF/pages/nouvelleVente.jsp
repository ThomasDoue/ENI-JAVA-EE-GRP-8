<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/nouvelleVente" method="post">

	
	<h1>Nouvelle vente</h1>
	
<img src="${image}" alt="${detailImageVente}"/>

	<div>
		<label for="article">Article :</label> 
		<input type="text" id="article" name="article" class="form-control" value="article" required minlenght="1" maxlength="4" > 
		
		
		<label for="article">Description :</label> 
		<textarea id="story" name="description" rows="5" cols="33" value="description">description du produit </textarea>
	</div>
	<div>
		<label for="categorie">Categorie :</label>
		<select name="categorieMenu" id="categorieMenu">
		    <option value="">--Selectionner un menu--</option>
		    <option value="parrot">Parrot</option>
		    <option value="spider">Spider</option>
		    <option value="goldfish">Goldfish</option>
		</select>
	</div>	 
	<div>
		<label for="photoArticle">Photo de l'article</label>
		<input type="submit" name="uploader" value="uploader">
	</div>
	<div>
		<label for="miseaPrix">Mise à prix :</label>
		<input type="number" step="1" value="0" min="0" max="10000">
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