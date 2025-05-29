package br.vianna.webzoo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(length = 50,nullable = false)
    private String nome;

    @Column(length = 60,nullable = false)
    private String senha;

    @Column(length = 60,nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 15,nullable = false)
    private ETipoUsuario tipo;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String senha, ETipoUsuario tipo, String email) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ETipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(ETipoUsuario tipo) {
        this.tipo = tipo;
    }
}
