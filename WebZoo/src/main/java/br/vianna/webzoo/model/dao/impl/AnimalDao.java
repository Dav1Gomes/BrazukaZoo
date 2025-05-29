package br.vianna.webzoo.model.dao.impl;

import br.vianna.webzoo.model.Animal;
import br.vianna.webzoo.model.dao.GenericsDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AnimalDao extends GenericsDAO<Animal, Integer> {

    public AnimalDao(EntityManager em) {
        this.connection = em;
    }

    @Override
    protected Class<Animal> getClasseEntidade() {
        return Animal.class;
    }

    @Override
    public Animal inserir(Animal obj) throws Exception {
        connection.getTransaction().begin();
        connection.persist(obj);
        connection.getTransaction().commit();
        return obj;
    }

    @Override
    public Animal alterar(Animal obj) throws Exception {
        connection.getTransaction().begin();
        Animal a = connection.merge(obj);
        connection.getTransaction().commit();
        return a;
    }

    @Override
    public Animal apagar(Animal obj) throws Exception {
        connection.getTransaction().begin();
        Animal a = connection.find(Animal.class, obj.getId());
        if (a != null) {
            connection.remove(a);
        }
        connection.getTransaction().commit();
        return a;
    }

    @Override
    public Animal apagarByKey(Integer key) throws Exception {
        return apagar(buscar(key));
    }

    @Override
    public Animal buscar(Integer key) {
        return connection.find(Animal.class, key);
    }

    @Override
    public List<Animal> buscarTodos() {
        TypedQuery<Animal> query = connection.createQuery("SELECT a FROM Animal a", Animal.class);
        return query.getResultList();
    }

    public List<Animal> buscarAleatorios(int quantidade) {
        return connection.createQuery("SELECT a FROM Animal a ORDER BY FUNCTION('RAND')", Animal.class)
                .setMaxResults(quantidade)
                .getResultList();
    }
}
