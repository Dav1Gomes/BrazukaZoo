<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="br.vianna.webzoo.model.Animal" %>

<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">

<main class="admin-container">
    <h2>Registrar Transferência de Animal</h2>
    <c:if test="${not empty mensagemSucesso}">
        <div class="alert-success">${mensagemSucesso}</div>
    </c:if>
    <c:if test="${not empty mensagemErro}">
        <div class="alert-success" style="background-color:#f8d7da; color:#721c24; border-left: 6px solid #f44336;">
                ${mensagemErro}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/web?ac=registrarTransferencia" method="post" class="formulario-admin">

       <%--@declare id="recintodestino"--%>
        <label for="idAnimal">Animal:</label>
        <select name="idAnimal" id="idAnimal" required onchange="atualizarRecintoOrigem()">
            <option value="">Selecione um animal</option>
            <c:forEach var="a" items="${listaAnimais}">
                <option value="${a.id}">${a.nome} (${a.especie})</option>
            </c:forEach>
        </select>

        <label for="recintoOrigem">Recinto de Origem:</label>
        <input type="text" name="recintoOrigem" id="recintoOrigem" readonly required>

        <label for="recintoDestino">Recinto de Destino:</label>
        <input type="text" name="recintoDestino" id="recintoDestino" required>

        <label for="motivo">Motivo da Transferência:</label>
        <textarea name="motivo" id="motivo" rows="4" required></textarea>

        <button type="submit" class="cta-button">Registrar</button>
    </form>
</main>
<script>
    window.mapaRecintos = {
        <c:forEach var="entry" items="${mapaRecintos}">
        "${entry.key}": "${entry.value}",
        </c:forEach>
    };
</script>
<script src="${pageContext.request.contextPath}/assets/js/transferencia.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/mensagens.js"></script>
<jsp:include page="/includes/footer.jsp" />
