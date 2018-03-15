package com.example.demo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
	
	/*---Interfaz para la gestion de datos de Personas---*/
	@Autowired
	PersonaRepository personaRepository;

	
	public List<Persona> getAll() {
		return personaRepository.findAll();
	}
		
	public Persona getByDni(String dni) {
		return personaRepository.getOne(dni);
	}
	
	public void add(Persona persona) {
		personaRepository.save(persona);
	}
	
	public void update(Persona persona) {
		Persona p = personaRepository.getOne(persona.getDni());
		p.setApellido(persona.getApellido());
		p.setNombre(persona.getNombre());
		personaRepository.saveAndFlush(p);
	}
	
	public void delete(String dni) {
		personaRepository.deleteById(dni);
	}
	
}
