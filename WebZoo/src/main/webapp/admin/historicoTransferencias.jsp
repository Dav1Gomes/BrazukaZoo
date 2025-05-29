<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/listaTransferencias.css">

<main class="admin-container visitas-admin">
    <h2>Histórico Geral de Transferências</h2>

    <c:if test="${not empty listaTransferencias}">
        <table class="tabela-visitas">
            <thead>
            <tr>
                <th>Animal</th>
                <th>Origem</th>
                <th>Destino</th>
                <th>Data e Hora</th>
                <th>Motivo</th>
                <th>Funcionário</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="t" items="${listaTransferencias}">
                <tr>
                    <td>${t.nomeAnimal}</td>
                    <td>${t.recintoOrigem}</td>
                    <td>${t.recintoDestino}</td>
                    <td>${t.dataHoraFormatada}</td>
                    <td>${t.motivo}</td>
                    <td>${t.nomeFuncionario}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty listaTransferencias}">
        <p>Nenhuma transferência encontrada.</p>
    </c:if>
</main>

<jsp:include page="/includes/footer.jsp" />
