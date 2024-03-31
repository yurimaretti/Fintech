<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="ISO-8859-1">
		<title>Fintech - Contas</title>
		<%@include file="head.jsp" %>
	</head>
	<body class="bg-light">
		<%@include file="header.jsp" %>	
		<div class="container">
			<div class="p-5 text-center">
				<h1 class="mb-3">Contas</h1>
			</div>
			<c:if test="${not empty msg}">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${not empty erro}">
				<div class="alert alert-danger">${erro}</div>
			</c:if>					
			<table class="table table-striped">
				<tr class="text-center">
					<th>Nome</th>
					<th>Saldo</th>
					<th>Descrição</th>
					<th>Cor</th>
					<th></th>
				</tr>
				<c:forEach items="${contas}" var="cta">
					<tr class="text-center">
						<td>${cta.nome}</td>
						<td>${cta.saldo}</td>
						<td>${cta.descricao}</td>
						<c:set var="text" value="${cta.cor.descricao}"/>	
					    <c:set var="text" value="${fn:toLowerCase(text)}"/>
						<td class="<c:out value="${text}"/>"></td>
						<td>
							<c:url value="conta" var="link">
								<c:param name="acao" value="abrir-form-edicao"/>
								<c:param name="codigo" value="${cta.codigo}"/>
							</c:url>
							<a href="${link}" class="btn btn-primary">
								<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
									<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
									<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
								</svg>
							</a>
							<!-- Button trigger modal -->
							<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="codigoExcluir.value = ${cta.codigo}">
								<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
									<path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/>
								</svg>
							</button>
							<c:url value="transacao" var="link_transacao">
								<c:param name="acao" value="listar"/>
								<c:param name="codigo" value="${cta.codigo}"/>
							</c:url>
							<a href="${link_transacao}" class="btn btn-success">
								Ver transações
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<a href="conta?acao=abrir-form-cadastro" class="btn btn-secondary">
				<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-plus-circle-fill" viewBox="0 0 16 16">
					<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
				</svg>
				Cadastrar Nova Conta
			</a>
			<c:url value="transacao" var="link_cadastro_transacao">
				<c:param name="acao" value="abrir-form-cadastro"/>
			</c:url>
			<a href="${link_cadastro_transacao}" class="btn btn-secondary">
				<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-plus-circle-fill" viewBox="0 0 16 16">
					<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
				</svg>
				Cadastrar Nova Transação
			</a>	
		</div>
		<%@include file="footer.jsp" %>
		
		<!-- Modal -->
		<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="staticBackdropLabel">Confirmação</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        Deseja realmente excluir a conta?
		      </div>
		      <div class="modal-body">
		        (Todas as transações dessa conta também serão excluídas)
		      </div>
		      <div class="modal-footer">
		      	<form action="conta" method="post">
		      		<input type="hidden" name="acao" value="excluir">
		      		<input type="hidden" name="codigo" id="codigoExcluir">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
			        <button type="submit" class="btn btn-danger">Excluir</button>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
		
	</body>
</html>