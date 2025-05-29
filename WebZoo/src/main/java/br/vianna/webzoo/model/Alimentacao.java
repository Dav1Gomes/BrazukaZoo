package br.vianna.webzoo.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "alimentacao")
public class Alimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal animal;

    @Column(name = "tipo_alimento", nullable = false)
    private String tipoAlimento;

    @Column(name = "data_hora", nullable = false)
    private Timestamp dataHora;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }

    public String getTipoAlimento() { return tipoAlimento; }
    public void setTipoAlimento(String tipoAlimento) { this.tipoAlimento = tipoAlimento; }

    public Timestamp getDataHora() { return dataHora; }
    public void setDataHora(Timestamp dataHora) { this.dataHora = dataHora; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
}
