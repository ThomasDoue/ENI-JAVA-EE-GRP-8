<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Accueil</title>
	<style><%@include file="/WEB-INF/css/style.css"%></style>
	<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
<meta charset="UTF-8">
<title>Accueil</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');
</style>

<body>
<form action="<%=request.getContextPath()%>/accueil" method="post">
	<label for="name">FILTRES :</label>
	<br>
	<input type="text" id="NomArticle" name="NomArticle" placeholder="Le nom de l'article contient" maxlength="30">
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
<div class ="mainListeEnchere">
	<div class="RadioButtonFiltre">
		<input type="radio" id="achats" name="choixfiltre" value="achats" onclick="displayType()">
		<label >Achats</label>
	</div>
	<div class="BlockFiltreVente">
		<input type="radio" id="vente" name="choixfiltre" value="vente" onclick="displayType()">
		<label>Mes Ventes</label>
	</div>
	<div class="RadioButtonFiltre">
		<input type="checkbox" id="EnchereOuvert" name="EnchereOuvert">
		<label>enchères ouvertes</label>
	</div>
	<div class="BlockFiltreVente" >
		<input type="checkbox" id="MesVentesEnCours" name="MesVentesEnCours">
		<label>mes ventes en cours</label>
	</div>
	<div class="RadioButtonFiltre">
		<input type="checkbox" id="MesEnchères" name="MesEnchères">
		<label>mes enchères</label>
	</div>
	<div class="BlockFiltreVente" >
		<input type="checkbox" id="MesVentesNonDébutées" name="MesVentesNonDébutées">
		<label>mes ventes non débutées</label>
	</div>
	<div class="RadioButtonFiltre">
		<input type="checkbox" id="MesEnchèresNonRemportés" name="MesEnchèresNonRemportés">
		<label>mes enchères remportées</label>
	</div>
	<div class="BlockFiltreVente" >
		<input  type="checkbox" id="VentesTerminées" name="VentesTerminées">
		<label>ventes terminées</label>
	</div>
</div>
<br>
<br>
<br>
<button>Rechercher</button>
</form>

<c:forEach items="${ListEncheres}" var="Encheres">
<form action="<%=request.getContextPath()%>/DetailsEncheres" method="get">
    <button type="submit" class="btn-link" id="IdEnchere" name="IdEnchere" value ="${Encheres.noEnchere}">${Encheres.nomArticle}</button>
    </form>
    	<br>
    <label>Prix : ${Encheres.prixVente}</label>
    	<br>
    <label>Fin de l'enchère ${Encheres.dateFinEncheres}</label>
    	<br>
   	<form action="<%=request.getContextPath()%>/RetourFormulaire" method="post">
    <label>Vendeur : ${Encheres.pseudo}</label>
    <button type="submit" class="btn-link" id="IdEnchere" name="IdEnchere" value ="${Encheres.noEnchere}">Vendeur : ${Encheres.pseudo}</button>
    </form>
    	<br>
    	<br>
    	<br>
</c:forEach>
</body>
<footer>
<form>
	 <input type="button" value="Rafraichir." onClick="refresh">
	 <input type="button" value="Précedent." onclick="history.back()">
</form>
</footer>
	</body>
<script>

window.displayType = function () {
	document.getElementById('EnchereOuvert').checked = false;
    document.getElementById('MesEnchères').checked = false;
    document.getElementById('MesEnchèresNonRemportés').checked = false;
    document.getElementById('VentesTerminées').checked = false;
    document.getElementById('MesVentesEnCours').checked = false;
    document.getElementById('MesVentesNonDébutées').checked = false;
	if(document.getElementById('achats').checked) {
        document.getElementById('EnchereOuvert').disabled = false;
        document.getElementById('MesEnchères').disabled = false;
        document.getElementById('MesEnchèresNonRemportés').disabled = false;
        document.getElementById('VentesTerminées').disabled = true;
        document.getElementById('MesVentesEnCours').disabled = true;
        document.getElementById('MesVentesNonDébutées').disabled = true;
    }
    else {
    	document.getElementById('EnchereOuvert').disabled = true;
        document.getElementById('MesEnchères').disabled = true;
        document.getElementById('MesEnchèresNonRemportés').disabled =true;
        document.getElementById('VentesTerminées').disabled = false;
        document.getElementById('MesVentesEnCours').disabled = false;
        document.getElementById('MesVentesNonDébutées').disabled = false;
  
    }
}

</script>
</html>

