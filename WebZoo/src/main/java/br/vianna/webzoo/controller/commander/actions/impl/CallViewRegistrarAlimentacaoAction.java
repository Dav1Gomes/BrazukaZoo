package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Animal;
import br.vianna.webzoo.model.dao.impl.AnimalDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.List;

public class CallViewRegistrarAlimentacaoAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            AnimalDao dao = new AnimalDao(em);
            List<Animal> lista = dao.buscarTodos();
            req.setAttribute("listaAnimais", lista);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensagemErro", "Erro ao carregar lista de animais.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/funcionario/registrarAlimentacao.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
