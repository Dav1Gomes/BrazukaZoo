package br.vianna.webzoo.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "funcionario")
public class Funcionario {

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 14, nullable = false)
    private String cpf;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 50)
    private String cargo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_contratacao")
    private Date dataContratacao;

    @Column(length = 20)
    private String turno;

    @Column(length = 100, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private ETipoUsuario tipo = ETipoUsuario.FUNCIONARIO;


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public Date getDataContratacao() { return dataContratacao; }
    public void setDataContratacao(Date dataContratacao) { this.dataContratacao = dataContratacao; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public ETipoUsuario getTipo() { return tipo; }
    public void setTipo(ETipoUsuario tipo) { this.tipo = tipo; }
}
