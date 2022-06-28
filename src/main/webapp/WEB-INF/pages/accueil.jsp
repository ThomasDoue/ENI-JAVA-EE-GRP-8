<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
<h1>Eni App</h1>
<a href="<%=request.getContextPath()%>/accueil"><img alt="LogoEni" src="ressources/LogoEni.png"></a>
<form>
 <input type="button" value="Rafraichir." onClick="refresh">
 <input type="button" value="Précedent." onclick="history.back()">
</form>
</body>
</html>