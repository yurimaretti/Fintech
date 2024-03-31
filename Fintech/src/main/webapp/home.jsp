<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="ISO-8859-1">
		<title>Fintech - Login</title>
		<%@include file="head.jsp" %>	
	</head>
	<body class="bg-light">
		<%@include file="header.jsp" %>	
		<div class="p-5 text-center">
			<h1 class="mb-3 display-1">FINTECH</h1>
			<h3>Cuidando da sua segurança financeira</h3>
		</div>
		<c:if test="${not empty msg}">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<div class="container d-flex justify-content-center">
			<h1>BEM VINDO!</h1>
		</div>
		<%@include file="footer.jsp" %>	
	</body>
</html>