package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.demo.model.Libro;
import com.example.demo.model.Persona;

/**
 * Test basicos sobre la clase Libro
 * @author Cristian
 *
 */
public class LibroJUnit {
	
	public static Libro l;
	public static String nombre="Introducción a la Ingenieria del software";
	public static long isbn=111;
	public static Double precio=13.5;
	
	public static String nombrePersona="Introducción";
	public static String apellidoPersona="Software";
	public static String dniPersona="11111X";
	
	@BeforeClass
	public static void create_libro() {
		l=new Libro();
		l.setNombre(nombre);
		l.setIsbn13(isbn);
		l.setPrecio(precio);
		Persona persona=new Persona();
		persona.setNombre(nombrePersona);
		persona.setDni(dniPersona);
		persona.setApellido(apellidoPersona);
		l.setAutor(persona);
		l.setEditor(persona);
	}
	
	@Test
	public void libro() {
		assertEquals("Comprobando el nombre del libro",nombre,l.getNombre());
		assertEquals("Comprobando el isbn del libro",isbn,l.getIsbn13());
		assertEquals("Comprobando el precio del libro", precio, l.getPrecio());
		
	}
	
	@Test
	public void autor() {
		assertEquals("Comprobando el nombre del autor",nombrePersona,l.getAutor().getNombre());
		assertEquals("Comprobando el apellido del autor",apellidoPersona,l.getAutor().getApellido());
		assertEquals("Comprobando el dni del autor", dniPersona, l.getAutor().getDni());
		
	}
	
	@Test
	public void editor() {
		assertEquals("Comprobando el nombre del editor",nombrePersona,l.getEditor().getNombre());
		assertEquals("Comprobando el apellido del editor",apellidoPersona,l.getEditor().getApellido());
		assertEquals("Comprobando el dni del editor", dniPersona, l.getEditor().getDni());
		
	}
}
