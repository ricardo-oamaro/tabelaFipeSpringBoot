package br.com.carro.TabelaFipe.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuscaVeiculoPorMarca(@JsonAlias("modelos") List<BuscaVeiculo> modelos) {

    @Override
    public String toString() {
        return
                "modelos: " + modelos + "\n";
    }
}
