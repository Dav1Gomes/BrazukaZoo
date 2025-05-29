<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cadastroFuncionario.css">

<main class="pagina-cadastro">
    <div class="formulario-container">
        <h2>Cadastro de Funcionário</h2>

        <%
            String msgOk = (String) session.getAttribute("mensagemSucesso");
            if (msgOk != null) {
        %>
        <div class="alert-success"><%= msgOk %></div>
        <%
                session.removeAttribute("mensagemSucesso");
            }

            String msgErro = (String) session.getAttribute("mensagemErro");
            if (msgErro != null) {
        %>
        <div class="alert-error"><%= msgErro %></div>
        <%
                session.removeAttribute("mensagemErro");
            }
        %>

        <form action="${pageContext.request.contextPath}/web?ac=CallCadastrarFuncionarioAction" method="post" class="formulario-admin">
            <div class="form-row">
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" id="nome" required>
                </div>
                <div class="form-group">
                    <label for="cpf">CPF:</label>
                    <input type="text" name="cpf" id="cpf" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" id="email" required>
                </div>
                <div class="form-group">
                    <label for="telefone">Telefone:</label>
                    <input type="text" name="telefone" id="telefone" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="cargo">Cargo:</label>
                    <input type="text" name="cargo" id="cargo" required>
                </div>
                <div class="form-group">
                    <label for="dataContratacao">Data de Contratação:</label>
                    <input type="date" name="dataContratacao" id="dataContratacao" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="turno">Turno:</label>
                    <input type="text" name="turno" id="turno" required>
                </div>
                <div class="form-group">
                    <label for="senha">Senha:</label>
                    <input type="password" name="senha" id="senha" required>
                </div>
            </div>

            <div class="form-row">
                <button type="submit">Cadastrar Funcionário</button>
            </div>
        </form>
    </div>
</main>

<jsp:include page="/includes/footer.jsp" />
