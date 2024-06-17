package com.josepaternina.screenmatch;

import com.josepaternina.screenmatch.principal.Principal;
import com.josepaternina.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	@Autowired // Inyección de dependencia
	private SerieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);

		try {
			principal.mostrarMenu();
		} catch (Exception e) {
			System.out.println("--- Pequeño errror: ---\n" + e.getMessage());
		}

		/*
		EjemploStreams ejemploStreams= new EjemploStreams();
		ejemploStreams.muestraEjemplo();
		*/

	}

}
