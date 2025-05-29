package br.vianna.webzoo.model;

import javax.persistence.*;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 18, nullable = false)
    private String cnpj;

    @Column(length = 50, nullable = false)
    private String tipoFornecimento;

    @Column(length = 100)
    private String contato;

    @Column(length = 150)
    private String endereco;

    @Column(length = 100)
    private String responsavelEntrega;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }

    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getTipoFornecimento() { return tipoFornecimento; }

    public void setTipoFornecimento(String tipoFornecimento) { this.tipoFornecimento = tipoFornecimento; }

    public String getContato() { return contato; }

    public void setContato(String contato) { this.contato = contato; }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getResponsavelEntrega() { return responsavelEntrega; }

    public void setResponsavelEntrega(String responsavelEntrega) { this.responsavelEntrega = responsavelEntrega; }
}
