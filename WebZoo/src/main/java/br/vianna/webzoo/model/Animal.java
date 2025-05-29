package br.vianna.webzoo.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "animais")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String especie;
    private int idade;
    private String habitat;
    private String tipo;

    @Column(name = "data_chegada")
    private Date dataChegada;

    @Column(name = "estado_saude")
    private String estadoSaude;

    private String recinto;
    private boolean exibicao;

    @Column(name = "nome_cientifico")
    private String nomeCientifico;

    private String descricao;


    public Animal() {}

    public Animal(int id, String nome, String especie, String habitat, int idade, Date dataChegada, String estadoSaude, String recinto,String tipo, boolean exibicao, String nomeCientifico, String descricao) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.habitat = habitat;
        this.idade = idade;
        this.dataChegada = dataChegada;
        this.estadoSaude = estadoSaude;
        this.recinto = recinto;
        this.tipo = tipo;
        this.exibicao = exibicao;
        this.nomeCientifico = nomeCientifico;
        this.descricao = descricao;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getHabitat() { return habitat; }
    public void setHabitat(String habitat) { this.habitat = habitat; }

    public Date getDataChegada() { return dataChegada; }
    public void setDataChegada(Date dataChegada) { this.dataChegada = dataChegada; }

    public String getEstadoSaude() { return estadoSaude; }
    public void setEstadoSaude(String estadoSaude) { this.estadoSaude = estadoSaude; }

    public String getRecinto() { return recinto; }
    public void setRecinto(String recinto) { this.recinto = recinto; }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isExibicao() {
        return exibicao;
    }

    public void setExibicao(boolean exibicao) {
        this.exibicao = exibicao;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
