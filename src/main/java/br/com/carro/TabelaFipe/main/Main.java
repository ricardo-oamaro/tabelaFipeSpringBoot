package br.com.carro.TabelaFipe.main;

import br.com.carro.TabelaFipe.model.BuscaVeiculo;
import br.com.carro.TabelaFipe.model.BuscaVeiculoPorMarca;
import br.com.carro.TabelaFipe.model.InformacaoVeiculoEscolhido;
import br.com.carro.TabelaFipe.model.Veiculos;
import br.com.carro.TabelaFipe.service.ConsomeAPI;
import br.com.carro.TabelaFipe.service.ConverterDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    Scanner scanner = new Scanner(System.in);
    ConsomeAPI consomeAPI = new ConsomeAPI();
    ConverterDados converterDados = new ConverterDados();

    List<BuscaVeiculo> marcas = new ArrayList<>();

    public final String URL = "https://parallelum.com.br/fipe/api/v1/";

    public String exibeMenu() {
        System.out.println("***** OPÇÕES *****\n" + "1 - Carro\n" + "2 - Moto\n" + "3 - Caminhão\n" + "Digite a opção desejada: ");

        var veiculo = scanner.nextLine();

        switch (veiculo) {
            case "1" -> veiculo = "carros";
            case "2" -> veiculo = "motos";
            case "3" -> veiculo = "caminhoes";
            default -> {
                System.out.println("Opção inválida!");
                veiculo = exibeMenu();
            }
        }

        return veiculo;

    }

    public String exibeMarcaDeVeliculos(String veiculo) {

        var json = consomeAPI.obterDados(URL + veiculo + "/marcas");
        marcas = List.of(converterDados.obterDados(json, BuscaVeiculo[].class));
        marcas.forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta:");
        var codigoMarca = scanner.nextLine();

        return codigoMarca;
    }

    public void exibeVeiculosPorMarca(String veiculo, String codigoMarca) {

        List<BuscaVeiculo> veiculos = new ArrayList<>();

        var json = consomeAPI.obterDados(URL + veiculo + "/marcas/" + codigoMarca + "/modelos");
        var veiculosPorMarca = List.of(converterDados.obterDados(json, BuscaVeiculoPorMarca.class));

        for (var i = 0; i < veiculosPorMarca.size(); i++) {
            veiculos.addAll(veiculosPorMarca.get(i).modelos());
        }
        veiculos.forEach(System.out::println);

        System.out.println("Digite um trecho do nome do veículo para consulta: ");
        var nomeVeiculo = scanner.nextLine();

        List<BuscaVeiculo> modelosFiltrados =  veiculos.stream()
                .filter(v -> v.nome().toUpperCase().contains(nomeVeiculo.toUpperCase()))
                .toList();

        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o cod do modelo: ");
        var codModelo = scanner.nextLine();

        json = consomeAPI.obterDados(URL + veiculo + "/marcas/" + codigoMarca + "/modelos/" + codModelo + "/anos");
        List<BuscaVeiculo>  anos = List.of(converterDados.obterDados(json, BuscaVeiculo[].class));

        List<Veiculos> veiculosList = new ArrayList<>();

        for (var i = 0; i < anos.size(); i++) {
            json = consomeAPI.obterDados(URL + veiculo + "/marcas/" + codigoMarca + "/modelos/" + codModelo + "/anos/" + anos.get(i).codigo());
            var veiculoEscolhido = converterDados.obterDados(json, Veiculos.class);
            veiculosList.add(veiculoEscolhido);
        }

        System.out.println("Veiculos filtrados por ano: ");
        veiculosList.forEach(System.out::println);



    }



}
