package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Libro;
import com.example.demo.model.LibroService;
import com.example.demo.model.Persona;
import com.example.demo.model.PersonaService;

@SpringBootApplication
public class DemoApplication {

	// JPA
	// https://www.objectdb.com/java/jpa/entity/id

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(LibroService libroService, PersonaService personaService) {
		return (args) -> {
			// crear datos al arrancar la aplicación
			// se usa para meter datos de prueba
			Persona alice = new Persona("Alice", "Wonderland", "666");
			Persona ali = new Persona("Ali", "Baba", "777");
			personaService.add(alice);
			personaService.add(ali);

			Libro libro1 = new Libro(12312l, "Introduccion a Spring", 100d, alice, ali);
			Libro libro2 = new Libro(1122l, "Como aprobar introduccion a la ingeniería", 1000d, ali, alice);
			libroService.add(libro1);
			libroService.add(libro2);

			// ejemplo de como buscar por nombre
			Libro l1 = libroService.getByName("Introduccion a Spring");
			Libro libroInexistente = libroService.getByName("NONAME");
		};
	}

}
