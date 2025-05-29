package br.vianna.webzoo.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class GenericsDAO<C, K> {

    protected EntityManager connection;

    public GenericsDAO() {
        open();
    }

    public void open(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("webzoo_PU");
        connection = emf.createEntityManager();
    }

    public void close(){
        connection.close();
    }

    public long contarTodos() {
        String jpql = "SELECT COUNT(c) FROM " + getClasseEntidade().getSimpleName() + " c";
        return connection.createQuery(jpql, Long.class).getSingleResult();
    }

    protected abstract Class<C> getClasseEntidade();
    public abstract C inserir(C obj) throws Exception;
    public abstract C alterar(C obj) throws Exception;
    public abstract C apagar(C obj) throws Exception;
    public abstract C apagarByKey(K key) throws Exception;
    public abstract C buscar(K key);
    public abstract List<C> buscarTodos();


}

