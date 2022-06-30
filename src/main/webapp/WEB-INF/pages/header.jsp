<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style><%@include file="/WEB-INF/css/styleXavier.css"%></style>
</head>
<header>
<div class="headerPosition">
	<a class="logoPosition" href="<%=request.getContextPath()%>/accueil"><img class="imageOption"alt="LogoEni" src="ressources/LogoEni.png"></a>

	<form class="formPageStart">
		<button class="buttonHeader" type="button" value="Rafraichir." onClick="refresh">Rafraichir</button>
		<button class="buttonHeader" type="button" value="Précedent." onClick="history.back()">Précedent</button>
	</form>
	
	
	<form class="formPageEnd">
		<button class="buttonHeader">Enchères</button>
		<button class="buttonHeader">Vendre un article</button>
		<button class="buttonHeader">Mon profil</button>
		<button class="buttonHeader">Déconnexion</button>
	</form>
</div>
</header>
</html>