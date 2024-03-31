<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="ISO-8859-1">
		<title>Fintech - Editar Usuário</title>
		<%@include file="head.jsp" %>	
	</head>
	<body class="bg-light">
		<%@include file="header.jsp" %>	
		<div class="p-5 text-center">
			<h1 class="mb-3">Alterar dados pessoais</h1>
		</div>
		<c:if test="${not empty msg}">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<div class="container d-flex justify-content-center">
			<form class="my-2 col-md-6" action="login" method="post">
				<input type="hidden" value="editar" name="acao">
				<input type="hidden" value="${user}" name="usuario">
				<div class="form-group mb-3">
					<label for="id_nome" class="form-label">Nome</label>
					<input type="text" name="nome" class="form-control form-outline" id="id_nome" value="${usuario.nome}" maxlength="100" required>
				</div>
				<div class="form-group mb-3">
					<label for="id_data">Data de Nascimento</label>
					<input type="text" name="data" id="id_data" class="form-control" value='<fmt:formatDate value="${usuario.dataNascimento.time}" pattern="dd/MM/yyyy"/>' required>
				</div>						
				<div class="form-group mb-3">
					<label for="id_senha1" class="form-label">Informe sua senha</label>
					<input type="password" name="senha1" class="form-control" id="id_senha1" placeholder="Nova senha..." maxlength="7" onkeyup='check();' required>
				</div>
				<div class="form-group mb-3">
					<label for="id_senha2" class="form-label">Confirme sua senha</label>
					<input type="password" name="senha2" class="form-control" id="id_senha2" placeholder="Nova senha..." maxlength="7" onkeyup='check();' required>
				</div>
				<button type="submit" class="btn btn-primary d-inline" id="botao_edicao">Salvar</button>
				<a href="login.jsp">
					<button type="button" class="btn btn-danger">Cancelar</button>
				</a>
			</form>			
		</div>
		<div>
			<span id='message' class="container d-flex justify-content-center"></span>
		</div>
		<%@include file="footer.jsp" %>	
		<script type="text/javascript">
			var check = function() {
				  if (document.getElementById('id_senha1').value ==
				    document.getElementById('id_senha2').value) {
				    document.getElementById('message').style.color = 'green';
				    document.getElementById('message').innerHTML = 'Senhas combinam';
				    document.getElementById('botao_edicao').disabled = false;
				  } else {
				    document.getElementById('message').style.color = 'red';
				    document.getElementById('message').innerHTML = 'Senhas não combinam';
				    document.getElementById('botao_edicao').disabled = true;
				  }
				}
		</script>
	</body>
</html>