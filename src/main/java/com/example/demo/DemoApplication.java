package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Libro;
import com.example.demo.model.LibroRepository;
import com.example.demo.model.Persona;
import com.example.demo.model.PersonaRepository;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;

@SpringBootApplication
public class DemoApplication {

	// JPA
	// https://www.objectdb.com/java/jpa/entity/id

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(LibroRepository libroRepository, PersonaRepository personaRepository) {
		return (args) -> {
			// crear datos al arrancar la aplicación
			// se usa para meter datos de prueba
			Persona alice = new Persona("Alice", "Wonderland", "666");
			Persona ali = new Persona("Ali", "Baba", "777");
			personaRepository.save(alice);
			personaRepository.save(ali);

			Libro libro1 = new Libro(12312l, "Introduccion a Spring", 100d, alice, ali);
			Libro libro2 = new Libro(1122l, "Como aprobar introduccion a la ingeniería", 1000d, ali, alice);
			libroRepository.save(libro1);
			libroRepository.save(libro2);

		};
	}

}
