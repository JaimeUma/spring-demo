package com.example.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
