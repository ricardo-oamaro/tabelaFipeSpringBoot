package br.com.carro.TabelaFipe.main;

import br.com.carro.TabelaFipe.model.BuscaVeiculo;
import br.com.carro.TabelaFipe.model.BuscaVeiculoPorMarca;
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

//        List<BuscaVeiculoPorMarca> veiculos = new ArrayList<>();

        var json = consomeAPI.obterDados(URL + veiculo + "/marcas/" + codigoMarca + "/modelos");
        var veiculosPorMarca = List.of(converterDados.obterDados(json, BuscaVeiculoPorMarca.class));

        for (var i = 0; i < veiculosPorMarca.size(); i++) {
            for (var j = 0; j < veiculosPorMarca.get(i).modelos().size(); j++) {
                System.out.println(veiculosPorMarca.get(i).modelos().get(j));
            }
        }
    }

}
