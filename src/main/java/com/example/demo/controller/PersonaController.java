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

import com.example.demo.model.Persona;
import com.example.demo.model.PersonaService;

//Controlador de los objetos Persona
@Controller
public class PersonaController {

	@Autowired
	PersonaService personaService;

	/*---Devuelve el template de personas---*/
	@GetMapping("/personas")
	public String listPersonView(Model model) {
		model.addAttribute("personas", personaService.getAll());
		// devuelvo el template personas
		return "personas";
	}

	/*---Devuelve el formulario para crear una persona---*/
	@GetMapping("/personas/add")
	public String addPersonView(Persona persona, Model model) {
		model.addAttribute("personas", personaService.getAll());
		model.addAttribute("persona", persona);
		return "addPersona";
	}

	/*---Devuelve el formulario para editar una persona---*/
	@GetMapping("/personas/edit/{id}")
	public String editPersonView(@PathVariable("id") String id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("persona", personaService.getByDni(id));
		return "updatePersona";
	}

	/*---Anade una nueva persona al sistema---*/
	@PostMapping("/personas")
	public String save(@Valid Persona persona, BindingResult result, Model model) {
		try {
			personaService.add(persona);
			model.addAttribute("create", true);
		} catch (Exception er) {
			model.addAttribute("create", false);
		}
		return listPersonView(model);
	}

	/*---Actualiza una nueva persona del sistema---*/
	@PostMapping("/personas/update")
	public String update(@Valid Persona persona, Model model) {
		try {
			personaService.update(persona);
			model.addAttribute("udpate", true);
		} catch (Exception er) {
			model.addAttribute("update", false);
		}
		return listPersonView(model);
	}

	/*---Elimina una persona a partir de su DNI---*/
	@DeleteMapping("/personas/{id}")
	public String delete(@PathVariable("id") String id, Model model) {

		try {
			personaService.delete(id);
			model.addAttribute("delete", true);
		} catch (Exception er) {
			model.addAttribute("delete", false);
		}
		return listPersonView(model);
	}

}
