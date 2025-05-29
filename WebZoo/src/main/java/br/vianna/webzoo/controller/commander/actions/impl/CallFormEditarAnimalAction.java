package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Animal;
import br.vianna.webzoo.model.dao.impl.AnimalDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;

public class CallFormEditarAnimalAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        EntityManager em = null;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            em = JpaUtil.getEntityManager();
            AnimalDao dao = new AnimalDao(em);
            Animal animal = dao.buscar(id);
            req.setAttribute("animal", animal);
            dao.close();
            return "/admin/formEditarAnimal.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensagemErro", "Erro ao carregar dados do animal.");
            return "/admin/editarAnimal.jsp";
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
