<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="br.vianna.webzoo.model.Usuario" %>
<%@ page import="br.vianna.webzoo.model.Funcionario" %>

<%
    Object objLogado = session.getAttribute("usuarioLogado");
    String tipoUsuario = "";
    String nomeUsuario = "Visitante";
    String urlInicio = request.getContextPath() + "/index.jsp";

    if (objLogado instanceof Usuario) {
        Usuario usuario = (Usuario) objLogado;
        tipoUsuario = usuario.getTipo().toString();
        nomeUsuario = usuario.getNome();

        switch (tipoUsuario) {
            case "ADMIN":
                urlInicio = request.getContextPath() + "/admin/dashboard.jsp";
                break;
            case "FUNCIONARIO":
                urlInicio = request.getContextPath() + "/funcionario/dashboard.jsp";
                break;
            case "VISITANTE":
            default:
                urlInicio = request.getContextPath() + "/index.jsp";
                break;
        }

    } else if (objLogado instanceof Funcionario) {
        Funcionario funcionario = (Funcionario) objLogado;
        tipoUsuario = "FUNCIONARIO";
        nomeUsuario = funcionario.getNome();
        urlInicio = request.getContextPath() + "/funcionario/dashboard.jsp";
    }
%>

<header class="header-bar">
    <div class="logo-container">
        <img src="<%= request.getContextPath() %>/assets/img/logo-arara.png" alt="Logo BrazukaZoo" height="60px" />
        <h1 class="logo-title">BrazukaZoo</h1>
    </div>

    <nav class="user-nav">
        <a href="<%= urlInicio %>">Início</a>

        <% if (objLogado == null) { %>
        <a href="<%= request.getContextPath() %>/auth/login.jsp">Login</a>
        <% } else { %>
        <a href="<%= request.getContextPath() %>/logout">Logout</a>
        <% } %>
    </nav>
</header>
