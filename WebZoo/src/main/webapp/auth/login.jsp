<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Login - BrazukaZoo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/auth.css">
</head>
<body>
<%@ include file="../includes/header.jsp" %>

<div class="auth-container">
    <h2>Log<span>in</span></h2>
    <p>Acesse o BrazukaZoo</p>


    <form class="formLogin" action="${pageContext.request.contextPath}/validaLogin" method="POST">
        <div>
            <label>Email:</label>
            <input type="text" name="email" required />
        </div>

        <div>
            <label>Senha:</label>
            <input type="password" name="senha" required />
        </div>

        <div>
            <button type="submit">Entrar</button>
        </div>
    </form>

    <div class="register-link">
        Não tem conta? <a href="<%= request.getContextPath() %>/auth/registroVisitante.jsp">Registre-se</a>
    </div>
</div>

<%@ include file="../includes/footer.jsp" %>
</body>
</html>
