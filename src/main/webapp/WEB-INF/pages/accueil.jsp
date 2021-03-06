<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Accueil</title>
	<style><%@include file="/WEB-INF/css/style.css"%></style>
	<jsp:include page="/WEB-INF/pages/header.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Accueil</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');
</style>

<body class = "bodyAccueil">

<form action="<%=request.getContextPath()%>/accueil" method="post">

	<label for="name">FILTRES :</label>
	<br>
	<input class = "inputAccueil"type="text" id="NomArticle" name="NomArticle" placeholder="Le nom de l'article" maxlength="30">
	<br>
<label for="name">Catégorie : </label>
<select name="Categorie" id="categ-select">
<option value="0">--Choisissez une catégorie--</option>
<c:forEach items="${ListCategorie}" var="categ">
    <option value="${categ.noCategorie}" id = "Categorie">${categ.libelle}</option>
</c:forEach>
</select>
<br>
<br>
<br>
<c:set var = "idUser" value ="${sessionScope.idUtilisateur}"/>
<c:if test="${idUser != null}">
<div class ="mainListeEnchere">
		<div class="RadioButtonFiltre checkRadioBoxAccueil">
			<input type="radio" id="achats" name="choixfiltre" value="achats">
			<label >Achats</label>
		</div>
		<div class="BlockFiltreVente checkRadioBoxAccueil2">
			<input type="radio" id="vente" name="choixfiltre" value="vente">
			<label>Mes Ventes</label>
		</div>
		<div class="RadioButtonFiltre checkRadioBoxAccueil">
			<input type="checkbox" id="EnchereOuvert" name="EnchereOuvert">
			<label>enchères ouvertes</label>
		</div>
		<div class="BlockFiltreVente checkRadioBoxAccueil2" >
			<input type="checkbox" id="MesVentesEnCours" name="MesVentesEnCours">
			<label>mes ventes en cours</label>
		</div>
		<div class="RadioButtonFiltre checkRadioBoxAccueil">
			<input type="checkbox" id="MesEnchères" name="MesEnchères">
			<label>mes enchères</label>
		</div>
		<div class="BlockFiltreVente checkRadioBoxAccueil2" >
			<input type="checkbox" id="MesVentesNonDébutées" name="MesVentesNonDébutées">
			<label>mes ventes non débutées</label>
		</div>
		<div class="RadioButtonFiltre checkRadioBoxAccueil">
			<input type="checkbox" id="MesEnchèresNonRemportés" name="MesEnchèresNonRemportés">
			<label>mes enchères remportées</label>
		</div>
		<div class="BlockFiltreVente checkRadioBoxAccueil2" >
			<input  type="checkbox" id="scales" name="scales">
			<label>ventes terminées</label>
		</div>
</div>
<br>
<br>
<br>
</c:if>
<button class = "button">Rechercher</button>

</form>

<c:forEach items="${ListEncheres}" var="Encheres">
<fieldset>
<form action="<%=request.getContextPath()%>/DetailsEncheres" method="get">

	<c:if test="${idUser != null}">
    	<button class = "button" type="submit" class="btn-link" id="IdArticle" name="IdArticle" value ="${Encheres.noArticle}">${Encheres.nomArticle}</button>
    </c:if>
    <c:if test="${idUser == null}">
    	<label>Vendeur : ${Encheres.nomArticle}</label>
    </c:if>
    </form>
    	<br>
    <label>Prix : ${Encheres.prixVente}</label>
    	<br>
    <label>Fin de l'enchère ${Encheres.dateFinEncheres}</label>
    	<br>
   	<form action="<%=request.getContextPath()%>/RetourFormulaire" method="post">
    
    <c:set var = "idUser" value ="${sessionScope.idUtilisateur}"/>
	<c:if test="${idUser != null}">
    	<button class="button" type="submit" class="btn-link" id="IdEnchere" name="IdEnchere" value ="${Encheres.noArticle}">Vendeur : ${Encheres.pseudo}</button>
    </c:if>
    <c:if test="${idUser == null}">
    	<label>Vendeur : ${Encheres.pseudo}</label>
    </c:if>
    
    </form>
    </fieldset>
    	<br>
    	<br>
    	<br>
</c:forEach>
</body>
<footer>
<form>
	 <input class = "button" type="button" value="Rafraichir." onClick="refresh">
	 <input class = "button" type="button" value="Précedent." onclick="history.back()">
</form>
</footer>
	</body>
	<script>
window.displayType = function () {
	if(document.getElementById('achats').checked) {
		document.getElementById('EnchereOuvert').checked = true;
	    document.getElementById('MesEnchères').checked = true;
	    document.getElementById('MesEnchèresNonRemportés').checked = true;
        document.getElementById('EnchereOuvert').disabled = false;
        document.getElementById('MesEnchères').disabled = false;
        document.getElementById('MesEnchèresNonRemportés').disabled = false;
        document.getElementById('VentesTerminées').disabled = true;
        document.getElementById('MesVentesEnCours').disabled = true;
        document.getElementById('MesVentesNonDébutées').disabled = true;
        document.getElementById('VentesTerminées').checked = false;
        document.getElementById('MesVentesEnCours').checked = false;
        document.getElementById('MesVentesNonDébutées').checked = false;
    }else{
    	document.getElementById('EnchereOuvert').checked = false;
	    document.getElementById('MesEnchères').checked = false;
	    document.getElementById('MesEnchèresNonRemportés').checked = false;
        document.getElementById('VentesTerminées').disabled = false;
        document.getElementById('MesVentesEnCours').disabled = false;
        document.getElementById('MesVentesNonDébutées').disabled = false;
        document.getElementById('VentesTerminées').checked = true;
        document.getElementById('MesVentesEnCours').checked = true;
        document.getElementById('MesVentesNonDébutées').checked = true;
        document.getElementById('EnchereOuvert').disabled = true;
        document.getElementById('MesEnchères').disabled = true;
        document.getElementById('MesEnchèresNonRemportés').disabled = true;
    }
}
</script>
</html>