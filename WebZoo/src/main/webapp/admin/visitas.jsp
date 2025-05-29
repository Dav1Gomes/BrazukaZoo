<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/listaVisitas.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/alerts.css">
<jsp:include page="/includes/header.jsp" />
<main class="admin-container visitas-admin">
    <h2 style="text-align: center; color: #372D85;">📅 Visitas Agendadas</h2>

    <c:if test="${not empty listaVisitas}">
        <table class="tabela-visitas">
            <thead>
            <tr>
                <th>ID</th>
                <th>Visitante</th>
                <th>Data</th>
                <th>Horário</th>
                <th>Pessoas</th>
                <th>Observações</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="v" items="${listaVisitas}">
                <tr>
                    <td>${v.id}</td>
                    <td>${v.nomeVisitante}</td>
                    <td><fmt:formatDate value="${v.dataVisita}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${v.horario}" pattern="HH:mm"/></td>
                    <td>${v.quantidadePessoas}</td>
                    <td><c:out value="${v.observacoes != null ? v.observacoes : '-'}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${v.status eq 'Cancelada'}">
                                <span style="color: red; font-weight: bold;">Cancelada</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: green; font-weight: bold;">Ativa</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${v.status eq 'Ativa'}">
                            <form action="web" method="post" style="display:inline;">
                                <input type="hidden" name="ac" value="cancelarVisita"/>
                                <input type="hidden" name="id" value="${v.id}"/>
                                <button type="submit" class="botao-cancelar" onclick="return confirm('Tem certeza que deseja cancelar esta visita?')">Cancelar</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty listaVisitas}">
        <p style="text-align: center;">Nenhuma visita agendada encontrada.</p>
    </c:if>
</main>

<%
    String msg = (String) session.getAttribute("mensagem");
    if (msg != null) {
%>
<div class="alert-success">
    <%= msg %>
</div>
<%
        session.removeAttribute("mensagem");
    }
%>

<jsp:include page="/includes/footer.jsp" />
<script src="<%= request.getContextPath() %>/assets/js/mensagens.js"></script>
