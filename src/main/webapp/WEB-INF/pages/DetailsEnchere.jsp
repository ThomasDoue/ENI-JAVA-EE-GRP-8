<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Détail de la vente</title>
	<style><%@include file="/WEB-INF/css/style.css"%></style>
	<jsp:include page="/WEB-INF/pages/header.jsp"></jsp:include>
	<style>@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');</style>
	</head>
<c:if test="${Erreurs == true}">	
	<p>Vous n'avez pas assez de crédit pour enchérir.</p>	
</c:if>
<body class = "bodyDetailEnchere">
	<br>
	
	<form class = "DetailsEncheres" action="<%=request.getContextPath()%>/DetailsEncheres" method="post">
		<input type="hidden" value="${Enchere.noArticle}" name="noArticle" />
		<br>
		<% if("false".equals(request.getAttribute("estFini"))) {%>
			<h1>Détail Vente</h1>
		<% } else {%>
			<h1> Vente terminée </h1>
		<%} %>
		<input type="hidden" value="${Enchere.noArticle}" name="noArticle" />
		<br>
		<c:out value="Nom de l'article : ${Enchere.nomArticle}" />
		<br>
		<br>
		<c:out value="Description : ${Enchere.description}" />
		<br>
		<% if("false".equals(request.getAttribute("estFini"))) {%>
			<c:out value="Catégorie : ${Enchere.libelleCateg}" />
		<%}%>
		<br>
		<c:out value="Meilleur offre : ${Enchere.prixVente}" />
		<br>
		<c:out value="Mise a Prix : ${Enchere.prixInitial}" />
		<br>
		<% if("false".equals(request.getAttribute("estFini"))) {%>
			<c:out value="Date fin enchère : ${Enchere.dateFinEncheres}" />
		<%}%>
		<br>
		<c:out value="Retrait : ${Enchere.rueRetrait} ${Enchere.villeRetrait} ${Enchere.codePostalRetrait}" />
		<br>
		<c:out value="Vendeur : ${Enchere.pseudo}" />
		<br>
		<% if("true".equals(request.getAttribute("estFini"))) {%>
			<c:out value="Tel : ${Enchere.telephone}"/>
			<br>
		<%}%>
		<% if("false".equals(request.getAttribute("estFini"))) {%>
			<input class="inputDetailEnchere" type="number" name="offre"  min = "${Enchere.prixVente + 1}" value= "${Enchere.prixVente + 1}">
			<br>
			<button class = "button" type="submit" name="actionUtilisateur" value="encherir">Enchérir</button>
		<%} %>
		<% if("true".equals(request.getAttribute("estFini"))) {%>	
			<button class = "button" type="submit" name="actionUtilisateur" value="back">back</button>
		<%}%>
		<br/>
	</form>
</body>
</html>