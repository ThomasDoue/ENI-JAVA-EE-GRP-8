<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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
	
	
	<form >
		<button type="submit" name="actionUtilisateur" value="encheres">Enchères</button>
		<button type="submit" name="actionUtilisateur" value="vendreArticle">Vendre un article</button>
		<button type="submit" name="actionUtilisateur" value="monProfil">Mon profil</button>
		<button type="submit" name= "actionUtilisateur" value="deconnection"> Déconnexion</button>
	</form>
	</div>
	
</div>
</header>
</html>