package br.com.carro.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuscaVeiculo(@JsonAlias("codigo") String codigo,
                          @JsonAlias("nome") String nome) {


    @Override
    public String toString() {
        return
                "Cod: " + codigo +
                ", Nome: " + nome;
    }
}
