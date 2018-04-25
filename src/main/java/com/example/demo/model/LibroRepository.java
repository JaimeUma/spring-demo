package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
	
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
	// Además de los metodos de consulta básico indico que quiero un método que busque por nombre del libro
	// A través del nombre del metodo, spring sabe que tiene que hacer una consulta y devolver el libro con el nombre especificado
	// findBy"Nombre"    nombre debe existir en la clase libro
	Libro findByNombre(String bookName);
}
