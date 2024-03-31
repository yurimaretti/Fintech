<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="ISO-8859-1">
		<title>Fintech - Editar Conta</title>
		<%@include file="head.jsp" %>	
	</head>
	<body class="bg-light">
		<%@include file="header.jsp" %>	
		<div class="p-5 text-center">
			<h1 class="mb-3">Editar Conta</h1>
		</div>
		<c:if test="${not empty msg}">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<div class="container d-flex justify-content-center">
			<form class="my-2 col-md-6" action="conta" method="post">
				<input type="hidden" value="editar" name="acao">
				<input type="hidden" value="${conta.codigo}" name="codigo">
				<input type="hidden" value="${conta.saldo}" name="saldo">
				<div class="form-group mb-3">
					<label for="id_nome" class="form-label">Nome</label>
					<input type="text" name="nome" class="form-control form-outline" id="id_nome" value="${conta.nome}" maxlength="50" required>
				</div>
				<div class="form-group mb-3">
					<label for="id_descricao" class="form-label">Descrição</label>
					<input type="text" name="descricao" class="form-control" id="id_descricao" value="${conta.descricao}" maxlength="200" required>
				</div>
				<div class="form-group mb-3">
					<label for="id_cor" class="form-label">Cor</label>
					<select name="cor" id="id_cor" class="form-control">
						<option value="0">Cor</option>
						<c:forEach items="${cores}" var="c">
							<c:if test="${c.descricao == conta.cor.descricao}">
								<option class="${c.css}" selected>${c.descricao}</option>
							</c:if>
							<c:if test="${c.descricao != conta.cor.descricao}">
								<option class="${c.css}">${c.descricao}</option>
							</c:if>
						</c:forEach>
					</select>				
				</div>
				<button type="submit" class="btn btn-primary d-inline">Salvar</button>
				<a href="conta?acao=listar">
					<button type="button" class="btn btn-danger">Cancelar</button>
				</a>
			</form>			
		</div>
		<%@include file="footer.jsp" %>	
	</body>
</html>