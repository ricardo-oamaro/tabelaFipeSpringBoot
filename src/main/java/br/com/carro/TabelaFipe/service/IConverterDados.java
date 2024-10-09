package br.com.carro.TabelaFipe.service;

public interface IConverterDados {

    <T> T obterDados(String json, Class<T> classe);
}
