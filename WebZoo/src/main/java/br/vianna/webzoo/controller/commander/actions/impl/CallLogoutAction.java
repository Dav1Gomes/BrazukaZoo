package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CallLogoutAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getSession(true).setAttribute("mensagem", "Você saiu do sistema com sucesso!");
        resp.sendRedirect("template.jsp?page=home/home");
        return null;
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
