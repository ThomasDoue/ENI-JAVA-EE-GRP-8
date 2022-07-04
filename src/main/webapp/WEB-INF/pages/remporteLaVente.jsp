<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	<button type="button" name="back">Back</button>

</body>
</html>