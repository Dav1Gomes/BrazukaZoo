package br.vianna.webzoo.model.dao.impl;

import br.vianna.webzoo.model.Recinto;
import br.vianna.webzoo.model.dao.GenericsDAO;

import javax.persistence.TypedQuery;
import java.util.List;

public class RecintoDao extends GenericsDAO<Recinto, Integer> {

    @Override
    protected Class<Recinto> getClasseEntidade() {
        return Recinto.class;
    }

    @Override
    public Recinto inserir(Recinto obj) throws Exception {
        connection.getTransaction().begin();
        connection.persist(obj);
        connection.getTransaction().commit();
        return obj;
    }

    @Override
    public Recinto alterar(Recinto obj) throws Exception {
        connection.getTransaction().begin();
        Recinto r = connection.merge(obj);
        connection.getTransaction().commit();
        return r;
    }

    @Override
    public Recinto apagar(Recinto obj) throws Exception {
        connection.getTransaction().begin();
        Recinto r = connection.find(Recinto.class, obj.getId());
        if (r != null) connection.remove(r);
        connection.getTransaction().commit();
        return r;
    }

    @Override
    public Recinto apagarByKey(Integer key) throws Exception {
        return apagar(buscar(key));
    }

    @Override
    public Recinto buscar(Integer key) {
        return connection.find(Recinto.class, key);
    }

    @Override
    public List<Recinto> buscarTodos() {
        return connection.createQuery("SELECT r FROM Recinto r ORDER BY r.tipoAnimal, r.nome", Recinto.class)
                .getResultList();
    }

    public List<Recinto> listarPorTipo(String tipoAnimal) {
        return connection.createQuery("SELECT r FROM Recinto r WHERE r.tipoAnimal = :tipo", Recinto.class)
                .setParameter("tipo", tipoAnimal)
                .getResultList();
    }
}
