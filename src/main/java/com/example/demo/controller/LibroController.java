package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Libro;

// Controlador de los objetos Libro
@Controller
public class LibroController {
	/*---Interfaz para la gestion de datos de Libros---*/
	@Autowired
	LibroRepository repository;

	/*---Interfaz para la gestion de datos de Personas---*/
	@Autowired
	PersonaRepository personaRepository;

	/*---Devuelve el template HTML de libros---*/
	// petición recibida por get
	@GetMapping("/libros")
	public String listBookView(Model model) {
		// datos que seran accesibles desde la plantilla (frontend)
		model.addAttribute("libros", getLibros());
		// devuelvo el template libros
		return "libros";
	}

	/*---Devuelve el formulario para crear un libro---*/
	@GetMapping("/libros/add")
	public String addLibroView(Libro libro, Model model) {
		model.addAttribute("personas", personaRepository.findAll());
		model.addAttribute("libro", libro);
		return "addLibro";
	}

	/*---Devuelve el formulario para editar un libro---*/
	@GetMapping("/libros/edit/{id}")
	public String editLibroView(@PathVariable("id") Long id, Model model) {

		model.addAttribute("personas", personaRepository.findAll());
		model.addAttribute("libro", repository.getOne(id));
		return "updateLibro";
	}

	/*---Anade un nuevo libro al sistema y vuelve a la pantalla de consulta de libros---*/
	// petición recibida por post
	@PostMapping("/libros")
	public String save(@Valid Libro libro, BindingResult result, Model model) {
		try {
			repository.saveAndFlush(libro);
			model.addAttribute("create", true);
		} catch (Exception er) {
			model.addAttribute("create", false);
		}
		return listBookView(model);
	}

	/*---Actualiza un libro del sistema y vuelve a la pantalla de consulta de libros---*/
	// petición recibida por post
	@PostMapping("/libros/update")
	public String update(@Valid Libro libro, Model model) {
		try {
			updateLibro(libro);
			model.addAttribute("udpate", true);
		} catch (Exception er) {
			model.addAttribute("update", false);
		}
		return listBookView(model);
	}

	/*---Elimina un libro a partir de su ISBN y vuelve a la pantalla de consulta de libros---*/
	// petición recibida por delete
	@DeleteMapping("/libros/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		try {
			repository.deleteById(id);
			model.addAttribute("delete", true);
		} catch (Exception er) {
			model.addAttribute("delete", false);
		}
		return listBookView(model);
	}

	/*---Devuelve la lista de libros---*/
	private List<Libro> getLibros() {
		return repository.findAll();
	}

	/*---Actualiza un libro---*/
	private void updateLibro(Libro libro) {
		Libro l = repository.getOne(libro.getIsbn13());
		l.setEditor(libro.getEditor());
		l.setAutor(libro.getAutor());
		l.setNombre(libro.getNombre());
		l.setPrecio(libro.getPrecio());
		repository.saveAndFlush(l);
	}

}
