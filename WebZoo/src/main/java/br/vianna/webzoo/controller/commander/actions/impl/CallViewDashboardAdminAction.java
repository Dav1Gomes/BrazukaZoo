package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.ETipoUsuario;
import br.vianna.webzoo.model.dao.impl.AnimalDao;
import br.vianna.webzoo.model.dao.impl.UsuarioDao;
import br.vianna.webzoo.model.dao.impl.VisitaDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;

public class CallViewDashboardAdminAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();

            AnimalDao daoAnimal = new AnimalDao(em);
            VisitaDao daoVisita = new VisitaDao(em);
            UsuarioDao daoUsuario = new UsuarioDao(em);

            request.setAttribute("totalAnimais", daoAnimal.contarTodos());
            request.setAttribute("totalFuncionarios", daoUsuario.contarPorTipo(ETipoUsuario.FUNCIONARIO));
            request.setAttribute("totalVisitantes", daoUsuario.contarPorTipo(ETipoUsuario.VISITANTE));
            request.setAttribute("totalVisitas", daoVisita.contarTodos());
            request.setAttribute("totalFornecedores", 0);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao carregar dados do painel.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/admin/dashboard.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}

