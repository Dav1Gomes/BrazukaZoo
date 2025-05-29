package br.vianna.webzoo.model.dao.impl;

import br.vianna.webzoo.model.Fornecedor;
import br.vianna.webzoo.model.dao.GenericsDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FornecedorDao extends GenericsDAO<Fornecedor, Integer> {

    public FornecedorDao(EntityManager em) {
        this.connection = em;
    }

    @Override
    protected Class<Fornecedor> getClasseEntidade() {
        return Fornecedor.class;
    }

    @Override
    public Fornecedor inserir(Fornecedor obj) throws Exception {
        connection.getTransaction().begin();
        connection.persist(obj);
        connection.getTransaction().commit();
        return obj;
    }

    @Override
    public Fornecedor alterar(Fornecedor obj) throws Exception {
        connection.getTransaction().begin();
        Fornecedor atualizado = connection.merge(obj);
        connection.getTransaction().commit();
        return atualizado;
    }

    @Override
    public Fornecedor apagar(Fornecedor obj) throws Exception {
        connection.getTransaction().begin();
        Fornecedor f = connection.find(Fornecedor.class, obj.getId());
        if (f != null) {
            connection.remove(f);
        }
        connection.getTransaction().commit();
        return f;
    }

    @Override
    public Fornecedor apagarByKey(Integer key) throws Exception {
        return apagar(buscar(key));
    }

    @Override
    public Fornecedor buscar(Integer key) {
        return connection.find(Fornecedor.class, key);
    }

    @Override
    public List<Fornecedor> buscarTodos() {
        TypedQuery<Fornecedor> query = connection.createQuery(
                "SELECT f FROM Fornecedor f", Fornecedor.class
        );
        return query.getResultList();
    }
}
