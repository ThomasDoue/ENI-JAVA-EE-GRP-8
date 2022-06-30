<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<header>
<div class="headerposition">
<a class="logoposition" href="<%=request.getContextPath()%>/accueil"><img alt="LogoEni" src="ressources/LogoEni.png"></a>

<form class="formpagestart">
 <button class="button" type="button" value="Rafraichir." onClick="refresh">Rafraichir</button>
 <button class="button" type="button" value="Précedent." onclick="history.back()">Précedent</button>
</form>
<form class="formpageend">
	<button class="button">Enchères</button>
	<button class="button">Vendre un article</button>
	<button class="button">Mon profil</button>
	<button class="button">Déconnexion</button>
</form>
</div>
</header>
</html>