<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<style>@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');</style>
</head>
<header>
<div class="flexheader">
<div >
	

	<form class="formPageStart">
		<a href="<%=request.getContextPath()%>/accueil"><img alt="LogoEni" src="ressources/LogoEni.png"></a>
		<button value="Rafraichir." onClick="refresh">Rafraichir</button>
		<button value="Précedent." onClick="history.back()">Précedent</button>
	</form>
	</div>
	<div class="formepageend">
	
	
	<form action="header" method="post">

		<button type="submit" name="actionUtilisateur" value="encheres">Enchères</button>
		<button type="submit" name="actionUtilisateur" value="vendreArticle">Vendre un article</button>
		<select name="actionUtilisateurSelect" onchange="location = this.value;">
			<option selected>Mon profil</option>
		    <option value="RetourFormulaire">Afficher mon profil</option>
		    <option value="ProfilModifierSupprimerServlet">Modifier mon profil</option>
		</select>
		<button type="submit" name="actionUtilisateur" value="deconnexion">Deconnexion</button>
	
	</form>
	</div>
	
</div>
</header>
</html>