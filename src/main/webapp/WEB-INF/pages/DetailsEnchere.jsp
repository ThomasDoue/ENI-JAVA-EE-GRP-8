<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Détail de la vente</title>
	<style><%@include file="/WEB-INF/css/style.css"%></style>
	<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
<style>
@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');
</style>
</head>
<body>
<br>
<form class = "forminscription" action="<%=request.getContextPath()%>/DetailsEncheres" method="post">
<input type="hidden" value="${Enchere.noArticle}" name="noArticle" />
<br>
<c:out value="Nom de l'article : ${Enchere.nomArticle}" />
<br>
<c:out value="Description : ${Enchere.description}" />
<br>
<c:out value="Catégorie : ${Enchere.libelleCateg}" />
<br>
<c:out value="Meilleur offre : ${Enchere.prixVente}" />
<br>
<c:out value="Mise a Prix : ${Enchere.prixInitial}" />
<br>
<c:out value="Date fin enchère : ${Enchere.dateFinEncheres}" />
<br>
<c:out value="Retrait : ${Enchere.rueRetrait} ${Enchere.villeRetrait} ${Enchere.codePostalRetrait}" />
<br>
<c:out value="Vendeur : ${Enchere.pseudo}" />
<br>
<input type="number" name="offre" min = "${Enchere.prixVente}" value= "${Enchere.prixVente}">
<br>
<button>Enchérir</button>
</form>
</body>
</html>