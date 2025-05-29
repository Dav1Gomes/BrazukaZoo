package br.vianna.webzoo.model.dao.impl;

import br.vianna.webzoo.model.Funcionario;
import br.vianna.webzoo.model.dao.GenericsDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FuncionarioDAO extends GenericsDAO<Funcionario, Integer> {

    public FuncionarioDAO(EntityManager em) {
        this.connection = em;
    }

    @Override
    protected Class<Funcionario> getClasseEntidade() {
        return Funcionario.class;
    }

    @Override
    public Funcionario inserir(Funcionario obj) throws Exception {
        connection.getTransaction().begin();
        connection.persist(obj);
        connection.getTransaction().commit();
        return obj;
    }

    @Override
    public Funcionario alterar(Funcionario obj) throws Exception {
        connection.getTransaction().begin();
        Funcionario atualizado = connection.merge(obj);
        connection.getTransaction().commit();
        return atualizado;
    }

    @Override
    public Funcionario apagar(Funcionario obj) throws Exception {
        connection.getTransaction().begin();
        Funcionario f = connection.find(Funcionario.class, obj.getId());
        if (f != null) {
            connection.remove(f);
        }
        connection.getTransaction().commit();
        return f;
    }

    @Override
    public Funcionario apagarByKey(Integer key) throws Exception {
        return apagar(buscar(key));
    }

    @Override
    public Funcionario buscar(Integer key) {
        return connection.find(Funcionario.class, key);
    }

    @Override
    public List<Funcionario> buscarTodos() {
        TypedQuery<Funcionario> query = connection.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
        return query.getResultList();
    }

    public Funcionario buscarPorIdUsuario(int idUsuario) {
        TypedQuery<Funcionario> query = connection.createQuery(
                "SELECT f FROM Funcionario f WHERE f.usuario.id = :id", Funcionario.class);
        query.setParameter("id", idUsuario);
        return query.getResultStream().findFirst().orElse(null);
    }
}
