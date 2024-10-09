package br.com.carro.TabelaFipe;

import br.com.carro.TabelaFipe.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Main main = new Main();
		var veiculo = main.exibeMenu();
		var codMarca = main.exibeMarcaDeVeliculos(veiculo);
		main.exibeVeiculosPorMarca(veiculo, codMarca);
	}
}
