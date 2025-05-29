package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.ETipoUsuario;
import br.vianna.webzoo.model.Usuario;
import br.vianna.webzoo.model.dao.impl.UsuarioDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;

public class CallRegistroVisitanteAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String confirma = req.getParameter("confirmaSenha");

        if (!senha.equals(confirma)) {
            req.setAttribute("err", "As senhas não coincidem.");
            RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=registroVisitante");
            rd.forward(req, resp);
            return nome;
        }

        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            UsuarioDao dao = new UsuarioDao(em);

            if (dao.existeEmail(email)) {
                req.setAttribute("err", "Email já está cadastrado.");
                RequestDispatcher rd = req.getRequestDispatcher("template.jsp?page=registroVisitante");
                rd.forward(req, resp);
                return nome;
            }

            Usuario visitante = new Usuario();
            visitante.setNome(nome);
            visitante.setEmail(email);
            visitante.setSenha(senha);
            visitante.setTipo(ETipoUsuario.VISITANTE);

            dao.inserir(visitante);

            req.getSession().setAttribute("usuarioLogado", visitante);
            req.getSession().setAttribute("estaLogado", "1");
            req.getSession().setAttribute("mensagem", "Cadastro realizado com sucesso!");
            resp.sendRedirect("web?ac=viewHomePage");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("err", "Erro ao registrar visitante.");
            req.getRequestDispatcher("template.jsp?page=registroVisitante").forward(req, resp);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return nome;
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
