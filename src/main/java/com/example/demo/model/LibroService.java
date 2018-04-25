package com.example.demo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
	/*---Interfaz para la gestion de datos de Libros---*/
	@Autowired
	LibroRepository repository;

	/*---Devuelve la lista de libros---*/
	public List<Libro> getAll() {
		return repository.findAll();
	}

	/*---Actualiza un libro---*/
	public void update(Libro libro) {
		Libro l = repository.getOne(libro.getIsbn13());
		l.setEditor(libro.getEditor());
		l.setAutor(libro.getAutor());
		l.setNombre(libro.getNombre());
		l.setPrecio(libro.getPrecio());
		repository.saveAndFlush(l);
	}

	public Libro getByIsbn13(long isbn13) {
		return repository.getOne(isbn13);
	}
	
	public Libro getByName(String name) {
		return repository.findByNombre(name);
	}

	public void add(Libro libro) {
		repository.saveAndFlush(libro);
	}

	public void delete(long isbn13) {
		repository.deleteById(isbn13);
	}

}
