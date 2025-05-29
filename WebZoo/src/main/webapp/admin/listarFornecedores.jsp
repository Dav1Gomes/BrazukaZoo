<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/listaFornecedores.css">

<main class="admin-container visitas-admin">
    <h2 style="text-align: center; color: #372D85;">Fornecedores Cadastrados</h2>

    <c:if test="${not empty listaFornecedores}">
        <table class="tabela-visitas">
            <thead>
            <tr>
                <th>Nome</th>
                <th>CNPJ</th>
                <th>Tipo de Fornecimento</th>
                <th>Contato</th>
                <th>Endereço</th>
                <th>Responsável pela Entrega</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="f" items="${listaFornecedores}">
                <tr>
                    <td>${f.nome}</td>
                    <td>${f.cnpj}</td>
                    <td>${f.tipoFornecimento}</td>
                    <td>${f.contato}</td>
                    <td>${f.endereco}</td>
                    <td>${f.responsavelEntrega}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty listaFornecedores}">
        <p>Nenhum fornecedor cadastrado até o momento.</p>
    </c:if>

    <div style="margin-top: 2rem; text-align: center;">
        <a href="${pageContext.request.contextPath}/admin/cadastrarFornecedor.jsp" class="cta-button">Voltar para Cadastro</a>
    </div>
</main>

<jsp:include page="/includes/footer.jsp" />
