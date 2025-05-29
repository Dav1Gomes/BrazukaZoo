package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Animal;
import br.vianna.webzoo.model.dao.impl.AnimalDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

public class CallViewHomePageAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            AnimalDao dao = new AnimalDao(em);
            List<Animal> destaques = dao.buscarAleatorios(4);
            request.setAttribute("animaisDestaque", destaques);
            request.setAttribute("pagina", "home/home");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        try {
            RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
