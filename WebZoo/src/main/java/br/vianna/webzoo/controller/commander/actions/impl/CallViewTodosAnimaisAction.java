package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Animal;
import br.vianna.webzoo.model.dao.impl.AnimalDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import javax.persistence.EntityManager;
import java.util.List;

public class CallViewTodosAnimaisAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            AnimalDao dao = new AnimalDao(em);
            List<Animal> lista = dao.buscarTodos();

            request.setAttribute("listaAnimais", lista);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao carregar animais.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/home/todosAnimais.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
