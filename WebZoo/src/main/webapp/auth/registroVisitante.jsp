<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Visitante - BrazukaZoo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/auth.css">
</head>
<body style="min-height: 100vh; position: relative; padding-bottom: 80px;">
<%@ include file="../includes/header.jsp" %>

<div class="auth-container">
    <h2>Registro</h2>
    <p>Cadastre-se para visitar o BrazukaZoo</p>

    <form action="<%= request.getContextPath() %>/web" method="POST">
        <input type="hidden" name="ac" value="regVisitante" />

        <div>
            <label>Nome:</label>
            <input type="text" name="nome" required />
        </div>

        <div>
            <label>Email:</label>
            <input type="email" name="email" required />
        </div>

        <div>
            <label>Senha:</label>
            <input type="password" name="senha" required />
        </div>

        <div>
            <label>Confirmar Senha:</label>
            <input type="password" name="confirmaSenha" required />
        </div>

        <% if (request.getAttribute("err") != null) { %>
        <p style="color: red; margin-top: 10px;"><%= request.getAttribute("err") %></p>
        <% } %>

        <div>
            <button type="submit">Registrar</button>
        </div>
    </form>

    <div class="register-link">
        Já tem conta? <a href="<%= request.getContextPath() %>/auth/login.jsp">Faça login</a>
    </div>
</div>

<%@ include file="../includes/footer.jsp" %>
</body>
</html>
