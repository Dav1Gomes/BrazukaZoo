package br.vianna.webzoo.model;

import javax.persistence.*;

@Entity
@Table(name = "recinto")
public class Recinto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(name = "tipo_animal", nullable = false, length = 30)
    private String tipoAnimal;

    public Recinto() {
    }

    public Recinto(int id, String nome, String tipoAnimal) {
        this.id = id;
        this.nome = nome;
        this.tipoAnimal = tipoAnimal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
}
