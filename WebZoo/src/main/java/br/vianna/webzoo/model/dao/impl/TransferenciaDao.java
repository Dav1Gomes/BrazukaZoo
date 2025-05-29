package br.vianna.webzoo.model.dao.impl;

import br.vianna.webzoo.model.TransferenciaRecinto;
import br.vianna.webzoo.model.dao.GenericsDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransferenciaDao extends GenericsDAO<TransferenciaRecinto, Integer> {

    public TransferenciaDao(EntityManager em) {
        this.connection = em;
    }

    @Override
    protected Class<TransferenciaRecinto> getClasseEntidade() {
        return TransferenciaRecinto.class;
    }

    @Override
    public TransferenciaRecinto inserir(TransferenciaRecinto obj) throws Exception {
        connection.persist(obj);
        return obj;
    }

    @Override
    public TransferenciaRecinto alterar(TransferenciaRecinto obj) throws Exception {
        connection.getTransaction().begin();
        TransferenciaRecinto t = connection.merge(obj);
        connection.getTransaction().commit();
        return t;
    }

    @Override
    public TransferenciaRecinto apagar(TransferenciaRecinto obj) throws Exception {
        connection.getTransaction().begin();
        TransferenciaRecinto t = connection.find(TransferenciaRecinto.class, obj.getId());
        if (t != null) {
            connection.remove(t);
        }
        connection.getTransaction().commit();
        return t;
    }

    @Override
    public TransferenciaRecinto apagarByKey(Integer key) throws Exception {
        return apagar(buscar(key));
    }

    @Override
    public TransferenciaRecinto buscar(Integer key) {
        return connection.find(TransferenciaRecinto.class, key);
    }

    @Override
    public List<TransferenciaRecinto> buscarTodos() {
        TypedQuery<TransferenciaRecinto> query = connection.createQuery(
                "SELECT t FROM TransferenciaRecinto t JOIN FETCH t.animal ORDER BY t.dataHora DESC",
                TransferenciaRecinto.class);
        return query.getResultList();
    }

    public List<TransferenciaRecinto> buscarPorFuncionario(int idFuncionario) {
        TypedQuery<TransferenciaRecinto> query = connection.createQuery(
                "SELECT t FROM TransferenciaRecinto t JOIN FETCH t.animal WHERE t.funcionario.id = :id ORDER BY t.dataHora DESC",
                TransferenciaRecinto.class);
        query.setParameter("id", idFuncionario);
        return query.getResultList();
    }
}
