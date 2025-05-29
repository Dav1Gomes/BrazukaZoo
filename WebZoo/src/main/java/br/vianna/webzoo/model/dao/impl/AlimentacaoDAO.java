package br.vianna.webzoo.model.dao.impl;

import br.vianna.webzoo.model.Alimentacao;

import javax.persistence.EntityManager;
import java.util.List;

public class AlimentacaoDAO {

    private EntityManager em;

    public AlimentacaoDAO(EntityManager em) {
        this.em = em;
    }

    public void registrar(Alimentacao ali) {
        em.getTransaction().begin();
        em.persist(ali);
        em.getTransaction().commit();
    }

    public List<Alimentacao> buscarPorFuncionario(int idFuncionario) {
        return em.createQuery(
                        "SELECT a FROM Alimentacao a " +
                                "JOIN FETCH a.animal " +
                                "WHERE a.funcionario.id = :id " +
                                "ORDER BY a.dataHora DESC", Alimentacao.class)
                .setParameter("id", idFuncionario)
                .getResultList();
    }


    public List<Alimentacao> buscarTodas() {
        return em.createQuery(
                        "SELECT a FROM Alimentacao a " +
                                "JOIN FETCH a.animal " +
                                "JOIN FETCH a.funcionario " +
                                "ORDER BY a.dataHora DESC", Alimentacao.class)
                .getResultList();
    }

}
