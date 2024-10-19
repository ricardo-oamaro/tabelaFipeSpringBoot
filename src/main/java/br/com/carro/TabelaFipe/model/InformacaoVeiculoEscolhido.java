package br.com.carro.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InformacaoVeiculoEscolhido(@JsonAlias("Modelo") String modelo,
                                          @JsonAlias("Marca") String marca,
                                          @JsonAlias("AnoModelo") String anoModelo,
                                          @JsonAlias("Combustivel") String combustivel,
                                          @JsonAlias("Valor") String valor) {
}
