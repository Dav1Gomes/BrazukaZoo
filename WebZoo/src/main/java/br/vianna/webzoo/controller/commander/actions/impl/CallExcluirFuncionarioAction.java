package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Funcionario;
import br.vianna.webzoo.model.dao.impl.FuncionarioDAO;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;

public class CallExcluirFuncionarioAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            em = JpaUtil.getEntityManager();
            FuncionarioDAO dao = new FuncionarioDAO(em);
            Funcionario f = dao.buscar(id);
            dao.apagar(f);

            request.getSession().setAttribute("mensagemSucesso", "Funcionário removido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensagemErro", "Erro ao remover funcionário.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "web?ac=viewFuncionarios";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
