package br.vianna.webzoo.model.dao.impl;

import br.vianna.webzoo.model.ETipoUsuario;
import br.vianna.webzoo.model.Usuario;
import br.vianna.webzoo.model.dao.GenericsDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsuarioDao extends GenericsDAO<Usuario, Integer> {

    public UsuarioDao(EntityManager em) {
        this.connection = em;
    }

    @Override
    protected Class<Usuario> getClasseEntidade() {
        return Usuario.class;
    }

    @Override
    public Usuario inserir(Usuario obj) throws Exception {
        connection.getTransaction().begin();
        connection.persist(obj);
        connection.getTransaction().commit();
        return obj;
    }

    @Override
    public Usuario alterar(Usuario obj) throws Exception {
        connection.getTransaction().begin();
        Usuario u = connection.merge(obj);
        connection.getTransaction().commit();
        return u;
    }

    @Override
    public Usuario apagar(Usuario obj) throws Exception {
        connection.getTransaction().begin();
        Usuario u = connection.find(Usuario.class, obj.getId());
        if (u != null) {
            connection.remove(u);
        }
        connection.getTransaction().commit();
        return u;
    }

    @Override
    public Usuario apagarByKey(Integer key) throws Exception {
        return apagar(buscar(key));
    }

    @Override
    public Usuario buscar(Integer key) {
        return connection.find(Usuario.class, key);
    }

    @Override
    public List<Usuario> buscarTodos() {
        TypedQuery<Usuario> query = connection.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }

    public Usuario buscarPorLoginSenha(String email, String senha) {
        TypedQuery<Usuario> query = connection.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        List<Usuario> lista = query.getResultList();
        return lista.isEmpty() ? null : lista.get(0);
    }

    public boolean existeEmail(String email) {
        TypedQuery<Usuario> query = connection.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
        query.setParameter("email", email);
        return !query.getResultList().isEmpty();
    }

    public long contarVisitantes() {
        TypedQuery<Long> query = connection.createQuery(
                "SELECT COUNT(u) FROM Usuario u WHERE u.tipo = :tipo", Long.class);
        query.setParameter("tipo", ETipoUsuario.VISITANTE);
        return query.getSingleResult();
    }

    public long contarPorTipo(ETipoUsuario tipo) {
        TypedQuery<Long> query = connection.createQuery(
                "SELECT COUNT(u) FROM Usuario u WHERE u.tipo = :tipo", Long.class);
        query.setParameter("tipo", tipo);
        return query.getSingleResult();
    }
}
