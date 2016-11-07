package project.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import project.Application;
import project.exceptions.EntityNotFoundException;
import project.models.UserModel;
import project.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GlobalExceptionController controllerAdvice;

	@Autowired
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userServiceMock;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(controllerAdvice)
				.apply(springSecurity(springSecurityFilterChain)).build();
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void create() throws Exception {
		// @formatter:off
		UserModel user = new UserModel();
		user.setUsername("username");
		user.setPassword("password");
		user.setEnabled(true);
		
		UserModel expectedUser = new UserModel();
		expectedUser.setUsername("username");
		expectedUser.setPassword("password");
		expectedUser.setEnabled(true);
		when(userServiceMock.create(any())).thenReturn(expectedUser);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsBytes(user))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.username", is("username")))
			.andExpect(jsonPath("$.password", is("password")))
			.andExpect(jsonPath("$.enabled", is(true)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void read() throws Exception {
		// @formatter:off
		UserModel expectedUser = new UserModel();
		expectedUser.setUsername("username");
		expectedUser.setPassword("password");
		expectedUser.setEnabled(true);
		when(userServiceMock.loadUserByUsername("username")).thenReturn(expectedUser);

		mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", "username")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.username", is("username")))
			.andExpect(jsonPath("$.password", is("password")))
			.andExpect(jsonPath("$.enabled", is(true)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void readNotFound() throws Exception {
		// @formatter:off
		when(userServiceMock.loadUserByUsername("username")).thenThrow(new EntityNotFoundException("resource.not_found", null));

		mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", "username")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void delete() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", "username")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void deleteNotFound() throws Exception {
		// @formatter:off
		doThrow(new EntityNotFoundException("resource.not_found", null)).when(userServiceMock).delete("username");
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", "username")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void getAll() throws Exception {
		// @formatter:off
		Page<UserModel> expectedUsers = new PageImpl<UserModel>(Arrays.asList(new UserModel()));
		when(userServiceMock.getAll(any(), any(), any(), any())).thenReturn(expectedUsers);

		mockMvc.perform(MockMvcRequestBuilders.get("/users")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$.content", hasSize(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}
}
