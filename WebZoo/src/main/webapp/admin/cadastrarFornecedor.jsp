<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.vianna.webzoo.model.Fornecedor" %>
<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cadastroFornecedor.css">

<main class="pagina-cadastro">
    <div class="formulario-container">
        <h2>Cadastro de Fornecedor</h2>

        <%
            String msg = (String) session.getAttribute("mensagemSucesso");
            if (msg != null) {
        %>
        <div class="alert-success"><%= msg %></div>
        <%
                session.removeAttribute("mensagemSucesso");
            }
        %>

        <form action="${pageContext.request.contextPath}/web?ac=cadastrarFornecedor" method="post" class="formulario-admin">
            <div class="form-row">
                <div class="form-group">
                    <label>Nome:</label>
                    <input type="text" name="nome" required>
                </div>
                <div class="form-group">
                    <label>CNPJ:</label>
                    <input type="text" name="cnpj" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Tipo de Fornecimento:</label>
                    <input type="text" name="tipoFornecimento" required>
                </div>
                <div class="form-group">
                    <label>Contato:</label>
                    <input type="text" name="contato">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Endereço:</label>
                    <input type="text" name="endereco">
                </div>
                <div class="form-group">
                    <label>Responsável pela Entrega:</label>
                    <input type="text" name="responsavelEntrega">
                </div>
            </div>

            <div class="form-row">
                <button type="submit">Cadastrar Fornecedor</button>
            </div>
        </form>
    </div>
</main>


<jsp:include page="/includes/footer.jsp" />
