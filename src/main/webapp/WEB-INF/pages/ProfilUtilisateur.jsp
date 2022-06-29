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
</head>
<body>

  <h1>Retour Formulaire</h1>

<ul>
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
			<li>
				Credit : ${credit}
			</li>
		</ul>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>