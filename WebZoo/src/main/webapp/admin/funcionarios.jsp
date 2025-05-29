<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.vianna.webzoo.model.Funcionario" %>
<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/listaFuncionarios.css">


<main class="admin-container visitas-admin">
    <h2 style="text-align: center; color: #372D85;">Funcionários Cadastrados</h2>

    <c:if test="${not empty sessionScope.mensagemSucesso}">
        <div class="alert-success">${sessionScope.mensagemSucesso}</div>
    </c:if>
    <c:if test="${not empty sessionScope.mensagemErro}">
        <div class="alert-success" style="background-color:#f8d7da; color:#721c24; border-left: 6px solid #f44336;">
                ${sessionScope.mensagemErro}
        </div>
    </c:if>
    <%
        session.removeAttribute("mensagemSucesso");
        session.removeAttribute("mensagemErro");
    %>


    <c:if test="${not empty listaFuncionarios}">
        <table class="tabela-visitas">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Email</th>
                <th>Telefone</th>
                <th>Cargo</th>
                <th>Data de Contratação</th>
                <th>Turno</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="f" items="${listaFuncionarios}">
                <tr>
                    <td>${f.id}</td>
                    <td>${f.nome}</td>
                    <td>${f.cpf}</td>
                    <td>${f.email}</td>
                    <td>${f.telefone}</td>
                    <td>${f.cargo}</td>
                    <td><fmt:formatDate value="${f.dataContratacao}" pattern="dd/MM/yyyy"/></td>
                    <td>${f.turno}</td>
                    <td>
                        <div class="acoes">
                            <a href="web?ac=formEditarFuncionario&id=${f.id}" class="cta-button">Editar</a>
                            <form action="web" method="post">
                                <input type="hidden" name="ac" value="excluirFuncionario"/>
                                <input type="hidden" name="id" value="${f.id}"/>
                                <button type="submit" class="botao-cancelar" onclick="return confirm('Deseja excluir este funcionário?')">Excluir</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty listaFuncionarios}">
        <p>Nenhum funcionário encontrado.</p>
    </c:if>
</main>

<jsp:include page="/includes/footer.jsp" />
