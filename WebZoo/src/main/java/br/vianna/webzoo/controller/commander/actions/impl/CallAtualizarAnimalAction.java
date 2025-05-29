package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Animal;
import br.vianna.webzoo.model.dao.impl.AnimalDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.Date;

public class CallAtualizarAnimalAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String especie = request.getParameter("especie");
            String habitat = request.getParameter("habitat");
            int idade = Integer.parseInt(request.getParameter("idade"));
            Date dataChegada = Date.valueOf(request.getParameter("data_chegada"));
            String estadoSaude = request.getParameter("estado_saude");
            String recinto = request.getParameter("recinto");
            String tipo = request.getParameter("tipo");
            boolean exibicao = "true".equals(request.getParameter("exibicao"));
            String nomeCientifico = request.getParameter("nome_cientifico");
            String descricao = request.getParameter("descricao");

            Animal animal = new Animal(id, nome, especie, habitat, idade, dataChegada, estadoSaude, recinto, tipo, exibicao, nomeCientifico, descricao);

            em = JpaUtil.getEntityManager();
            AnimalDao dao = new AnimalDao(em);
            dao.alterar(animal);
            dao.close();

            request.getSession().setAttribute("mensagemSucesso", "Animal atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensagemErro", "Erro ao atualizar animal.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        response.sendRedirect("web?ac=editarAnimal");
        return null;
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
