package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


// Tests sobre el comportamiento de la aplicación al acceder a diferentes urls
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class MvcTest {

	private static final String SPRING_HOME_PAGE_TITLE = "Spring demo";

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	// homepage esta accesible sin loguear y contiene SPRING_HOME_PAGE_TITLE
	@Test
	@WithAnonymousUser
	public void homeTest() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(SPRING_HOME_PAGE_TITLE)));
	}

	// comprobar que accediendo directamente a la url libros con un usuario anonimo redirige a la pantalla de login 
	@Test
	@WithAnonymousUser
	public void librosTest() throws Exception {
		mockMvc.perform(get("/libros")).andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("http://*/login"));
	}

}
