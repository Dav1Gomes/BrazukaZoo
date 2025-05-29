package br.vianna.webzoo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencia_recinto")
public class TransferenciaRecinto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal animal;

    @Column(name = "recinto_origem", length = 100, nullable = false)
    private String recintoOrigem;

    @Column(name = "recinto_destino", length = 100, nullable = false)
    private String recintoDestino;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getRecintoOrigem() {
        return recintoOrigem;
    }

    public void setRecintoOrigem(String recintoOrigem) {
        this.recintoOrigem = recintoOrigem;
    }

    public String getRecintoDestino() {
        return recintoDestino;
    }

    public void setRecintoDestino(String recintoDestino) {
        this.recintoDestino = recintoDestino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
