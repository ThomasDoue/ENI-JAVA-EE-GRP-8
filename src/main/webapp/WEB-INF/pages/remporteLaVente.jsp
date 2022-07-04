<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Vous remportez la vente</title>
	<style><%@include file="/WEB-INF/css/style.css"%></style>
	<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
</head>
<body>
	<h1>Vous avez remporté la vente</h1>
	
	<ul>
		<li>${titre}</li>
		<li>Description : ${description}</li>
		<li>Meilleure offre : ${MeilleureOffre}</li>
		<li>Mise à prix : ${MiseAPrix}</li>
		<li>Retrait : ${adresse}<br/>${codePostal}<br/>${ville}</li>
		<li>Vendeur : ${pseudoVendeur}</li>
		<li>Tel : ${telephoneVendeur}</li>
	</ul>
	
	<form method="post">
		<button type="button" name="back">Back</button>
	</form>
	
</body>
</html>