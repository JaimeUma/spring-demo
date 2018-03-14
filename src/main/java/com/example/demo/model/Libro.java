package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Libro {

	// clave primaria
	@Id
	private long isbn13;

	private String nombre;
	private Double precio;

	@OneToOne
	private Persona autor;

	@OneToOne
	private Persona editor;

	public Libro() {

	}

	public Libro(long isbn13, String name, Double price, Persona autor, Persona editor) {
		this.nombre = name;
		this.precio = price;
		this.autor = autor;
		this.editor = editor;
		this.isbn13 = isbn13;
	}

	public Persona getEditor() {
		return editor;
	}

	public void setEditor(Persona editor) {
		this.editor = editor;
	}

	public long getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(long isbn13) {
		this.isbn13 = isbn13;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Persona getAutor() {
		return autor;
	}

	public void setAutor(Persona autor) {
		this.autor = autor;
	}

	public int hashCode() {
		try {
			return (int) isbn13;
		} catch (Exception er) {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		final Libro other = (Libro) obj;
		if (isbn13 != other.getIsbn13())
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Libro [isbn13=" + isbn13 + ", nombre=" + nombre + ", precio=" + precio + ", autor=" + autor
				+ ", editor=" + editor + "]";
	}

}