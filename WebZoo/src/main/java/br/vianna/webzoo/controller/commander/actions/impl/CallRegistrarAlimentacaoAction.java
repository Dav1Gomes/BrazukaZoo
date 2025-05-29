package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.*;
import br.vianna.webzoo.model.dao.impl.AlimentacaoDAO;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.sql.Timestamp;

public class CallRegistrarAlimentacaoAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            int idAnimal = Integer.parseInt(request.getParameter("idAnimal"));
            String tipoAlimento = request.getParameter("tipoAlimento");

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

            em = JpaUtil.getEntityManager();

            Animal animal = em.find(Animal.class, idAnimal);
            Funcionario funcionario = em.createQuery("SELECT f FROM Funcionario f WHERE f.email = :email", Funcionario.class)
                    .setParameter("email", usuario.getEmail())
                    .getSingleResult();

            Alimentacao ali = new Alimentacao();
            ali.setAnimal(animal);
            ali.setFuncionario(funcionario);
            ali.setTipoAlimento(tipoAlimento);
            ali.setDataHora(new Timestamp(System.currentTimeMillis()));

            new AlimentacaoDAO(em).registrar(ali);

            request.getSession().setAttribute("mensagemSucesso", "Alimentação registrada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensagemErro", "Erro ao registrar alimentação.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        try {
            response.sendRedirect(request.getContextPath() + "/web?ac=viewRegistrarAlimentacao");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
