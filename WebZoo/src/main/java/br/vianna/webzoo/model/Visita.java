package br.vianna.webzoo.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "visita")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_visitante")
    private int idVisitante;

    @Column(name = "data_visita")
    private Date dataVisita;

    private Time horario;

    @Column(name = "quantidade_pessoas")
    private int quantidadePessoas;

    private String observacoes;

    private String status = "Ativa";

    @Transient
    private String nomeVisitante;

    public Visita() {}

    public Visita(int id, int idVisitante, Date dataVisita, Time horario, int quantidadePessoas, String observacoes) {
        this.id = id;
        this.idVisitante = idVisitante;
        this.dataVisita = dataVisita;
        this.horario = horario;
        this.quantidadePessoas = quantidadePessoas;
        this.observacoes = observacoes;
        this.status = "Ativa";
    }

    public Visita(int id, int idVisitante, Date dataVisita, Time horario, int quantidadePessoas, String observacoes, String nomeVisitante) {
        this(id, idVisitante, dataVisita, horario, quantidadePessoas, observacoes);
        this.nomeVisitante = nomeVisitante;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdVisitante() { return idVisitante; }
    public void setIdVisitante(int idVisitante) { this.idVisitante = idVisitante; }

    public Date getDataVisita() { return dataVisita; }
    public void setDataVisita(Date dataVisita) { this.dataVisita = dataVisita; }

    public Time getHorario() { return horario; }
    public void setHorario(Time horario) { this.horario = horario; }

    public int getQuantidadePessoas() { return quantidadePessoas; }
    public void setQuantidadePessoas(int quantidadePessoas) { this.quantidadePessoas = quantidadePessoas; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNomeVisitante() { return nomeVisitante; }
    public void setNomeVisitante(String nomeVisitante) { this.nomeVisitante = nomeVisitante; }
}
