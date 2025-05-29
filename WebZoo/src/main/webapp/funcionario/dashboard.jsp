<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboardFuncionario.css">

<main class="dashboard-funcionario">
    <h2>Bem-vindo, ${usuarioLogado.nome}</h2>

    <div class="container-secoes">
        <div class="bloco-secao">
            <h3>Alimentar</h3>
            <a href="${pageContext.request.contextPath}/web?ac=viewRegistrarAlimentacao" class="cta-button">Registrar Alimentação</a>
            <a href="${pageContext.request.contextPath}/web?ac=viewHistoricoAlimentacao" class="cta-button">Histórico de Alimentações</a>
        </div>

        <div class="bloco-secao">
            <h3>Transferir</h3>
            <a href="${pageContext.request.contextPath}/web?ac=viewRegistrarTransferencia" class="cta-button">Registrar Transferência</a>
            <a href="${pageContext.request.contextPath}/web?ac=viewHistoricoTransferenciasFuncionario" class="cta-button">Histórico de Transferências</a>
        </div>
    </div>
</main>

<jsp:include page="/includes/footer.jsp" />
