<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>

<%
    String pagina = (String) request.getAttribute("pagina");
    if (pagina == null || pagina.trim().isEmpty()) {
        pagina = "home/home";
    }
%>

<main>
    <%
        RequestDispatcher rd = request.getRequestDispatcher(pagina + ".jsp");
        rd.include(request, response);
    %>
</main>
