<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">
<title>Retour Formulaire</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
</head>
<body class="bodyProfil">

  <h1>Mon profil</h1>
			<li>
				Pseudo : ${pseudo}
			</li>
			<li>
				Nom : ${nom}
			</li>
			<li>
				Prénom : ${prenom}
			</li>
			<li>
				Email : ${email}
			</li>
			<li>
				Telèphone : ${telephone}
			</li>
			<li>
				Rue : ${rue}
			</li>
			<li>
				Code Postal : ${codePostal}
			</li>
			<li>
				Ville : ${ville}
			</li>
			<% if(request.getAttribute("credit") != null) {%>
			<li>
				Credit : ${credit}
			</li>
			<%}%>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>