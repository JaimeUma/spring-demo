package com.example.demo.controller;

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
import com.example.demo.model.LibroService;
import com.example.demo.model.PersonaService;

// Controlador de los objetos Libro
@Controller
public class LibroController {

	@Autowired
	LibroService libroService;

	@Autowired
	PersonaService personaService;

	/*---Devuelve el template HTML de libros---*/
	// petición recibida por get
	@GetMapping("/libros")
	public String listBookView(Model model) {
		// datos que seran accesibles desde la plantilla (frontend)
		model.addAttribute("libros", libroService.getAll());
		// devuelvo el template libros
		return "libros";
	}

	/*---Devuelve el formulario para crear un libro---*/
	@GetMapping("/libros/add")
	public String addLibroView(Libro libro, Model model) {
		model.addAttribute("personas", personaService.getAll());
		model.addAttribute("libro", libro);
		return "addLibro";
	}

	/*---Devuelve el formulario para editar un libro---*/
	@GetMapping("/libros/edit/{isbn}")
	public String editLibroView(@PathVariable("isbn") Long isbn, Model model) {

		model.addAttribute("personas", personaService.getAll());
		model.addAttribute("libro", libroService.getByIsbn13(isbn));
		return "updateLibro";
	}

	/*---Anade un nuevo libro al sistema y vuelve a la pantalla de consulta de libros---*/
	// petición recibida por post
	@PostMapping("/libros")
	public String save(@Valid Libro libro, BindingResult result, Model model) {
		try {
			libroService.add(libro);
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
			libroService.add(libro);
			model.addAttribute("udpate", true);
		} catch (Exception er) {
			model.addAttribute("update", false);
		}
		return listBookView(model);
	}

	/*---Elimina un libro a partir de su ISBN y vuelve a la pantalla de consulta de libros---*/
	// petición recibida por delete
	@DeleteMapping("/libros/{isbn13}")
	public String delete(@PathVariable("isbn13") long isbn13, Model model) {
		try {
			libroService.delete(isbn13);
			model.addAttribute("delete", true);
		} catch (Exception er) {
			model.addAttribute("delete", false);
		}
		return listBookView(model);
	}

}
