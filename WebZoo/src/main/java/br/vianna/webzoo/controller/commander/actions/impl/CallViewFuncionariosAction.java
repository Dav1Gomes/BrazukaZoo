package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Funcionario;
import br.vianna.webzoo.model.dao.impl.FuncionarioDAO;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.List;

public class CallViewFuncionariosAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            FuncionarioDAO dao = new FuncionarioDAO(em);
            List<Funcionario> funcionarios = dao.buscarTodos();
            request.setAttribute("listaFuncionarios", funcionarios);
        } catch (Exception e) {
            request.setAttribute("mensagemErro", "Erro ao carregar funcionários.");
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/admin/funcionarios.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
