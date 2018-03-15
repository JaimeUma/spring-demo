package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Libro;
import com.example.demo.model.LibroService;

// Interfaz restful (No se usa en el frontend. Muestra como se implementaría servicios rest)
@RestController
public class LibroRestController {

	@Autowired
	LibroService libroService;

	/*---Add new libro---*/
	@PostMapping("/libro")
	public ResponseEntity<?> save(@RequestBody Libro libro) {
		libroService.add(libro);
		return ResponseEntity.ok().body("New libro has been saved");
	}

	/*---Get a libro by id---*/
	@GetMapping("/libro/{id}")
	public ResponseEntity<Libro> get(@PathVariable("id") long id) {
		Libro libro = libroService.getByIsbn13(id);
		return ResponseEntity.ok().body(libro);
	}

	/*---get all libros---*/
	@GetMapping("/libro")
	public ResponseEntity<List<Libro>> list() {
		List<Libro> libros = libroService.getAll();
		return ResponseEntity.ok().body(libros);
	}

	/*---Update a libro by id---*/
	@PutMapping("/libro/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Libro libro) {
		libroService.update(libro);
		return ResponseEntity.ok().body("libro has been updated successfully.");
	}

	/*---Delete a libro by id---*/
	@DeleteMapping("/libro/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		libroService.delete(id);
		return ResponseEntity.ok().body("libro has been deleted successfully.");
	}
}
