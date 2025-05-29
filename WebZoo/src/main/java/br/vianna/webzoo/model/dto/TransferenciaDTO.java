package br.vianna.webzoo.model.dto;

import br.vianna.webzoo.model.TransferenciaRecinto;

import java.time.format.DateTimeFormatter;

public class TransferenciaDTO {
    private String nomeAnimal;
    private String recintoOrigem;
    private String recintoDestino;
    private String dataHoraFormatada;
    private String motivo;
    private String nomeFuncionario;

    public TransferenciaDTO(TransferenciaRecinto t) {
        this.nomeAnimal = t.getAnimal().getNome();
        this.recintoOrigem = t.getRecintoOrigem();
        this.recintoDestino = t.getRecintoDestino();
        this.dataHoraFormatada = t.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.motivo = t.getMotivo();
        this.nomeFuncionario = t.getFuncionario().getNome();
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public String getRecintoOrigem() {
        return recintoOrigem;
    }

    public String getRecintoDestino() {
        return recintoDestino;
    }

    public String getDataHoraFormatada() {
        return dataHoraFormatada;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }
}
