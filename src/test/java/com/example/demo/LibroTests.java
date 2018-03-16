package com.example.demo;

import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Libro;
import com.example.demo.model.LibroRepository;
import com.example.demo.model.LibroService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibroTests {

	private static final long ISBN_TEST = 123l;
	private static final long ISBN_TEST2 = 0l;

	@MockBean
	LibroRepository libroRepository;

	@Autowired
	public LibroService libroService;

	@Test
	public void contextLoads() {
		assertNotNull(libroService);
	}

	@Test
	public void testGetOne() {
		Libro dummyLibro = new Libro();
		dummyLibro.setIsbn13(ISBN_TEST);
		dummyLibro.setNombre("titulo");

		Libro dummyLibro2 = new Libro();
		dummyLibro2.setIsbn13(ISBN_TEST2);
		dummyLibro2.setNombre("titulo2");

		when(libroRepository.getOne(anyLong())).thenReturn(dummyLibro2);
		when(libroRepository.getOne(ISBN_TEST)).thenReturn(dummyLibro);

		Libro resultLibro = libroService.getByIsbn13(ISBN_TEST);
		verify(libroRepository, times(1)).getOne(ISBN_TEST);
		assertEquals(dummyLibro, resultLibro);

		Libro resultLibro2 = libroService.getByIsbn13(ISBN_TEST2);
		assertEquals(dummyLibro2, resultLibro2);
	}
}
