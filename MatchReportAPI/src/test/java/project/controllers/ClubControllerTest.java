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
import project.models.ClubModel;
import project.services.ClubService;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class ClubControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GlobalExceptionController controllerAdvice;

	@Autowired
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@InjectMocks
	private ClubController clubController;

	@Mock
	private ClubService clubServiceMock;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(clubController)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(controllerAdvice)
				.apply(springSecurity(springSecurityFilterChain)).build();
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void create() throws Exception {
		// @formatter:off
		ClubModel club = new ClubModel();
		club.setName("name");
		club.setAddress("address");
		club.setStadium(null);
		club.setUrl("url");
		club.setImage("image");
		
		ClubModel expectedClub = new ClubModel();
		expectedClub.setId(1);
		expectedClub.setName("name");
		expectedClub.setAddress("address");
		expectedClub.setStadium(null);
		expectedClub.setUrl("url");
		expectedClub.setImage("image");
		when(clubServiceMock.create(any())).thenReturn(expectedClub);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/clubs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsBytes(club))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("name")))
			.andExpect(jsonPath("$.address", is("address")))
			.andExpect(jsonPath("$.stadium").doesNotExist())
			.andExpect(jsonPath("$.url", is("url")))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		ClubModel expectedClub = new ClubModel();
		expectedClub.setId(1);
		expectedClub.setName("name");
		expectedClub.setAddress("address");
		expectedClub.setStadium(null);
		expectedClub.setUrl("url");
		expectedClub.setImage("image");
		when(clubServiceMock.read(1)).thenReturn(expectedClub);

		mockMvc.perform(MockMvcRequestBuilders.get("/clubs/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("name")))
			.andExpect(jsonPath("$.address", is("address")))
			.andExpect(jsonPath("$.stadium").doesNotExist())
			.andExpect(jsonPath("$.url", is("url")))
			.andExpect(jsonPath("$.image", is("image")))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void readNotFound() throws Exception {
		// @formatter:off
		when(clubServiceMock.read(1)).thenThrow(new EntityNotFoundException("resource.not_found", null));

		mockMvc.perform(MockMvcRequestBuilders.get("/clubs/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void delete() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.delete("/clubs/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void deleteNotFound() throws Exception {
		// @formatter:off
		doThrow(new EntityNotFoundException("resource.not_found", null)).when(clubServiceMock).delete(1);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/clubs/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		Page<ClubModel> expectedClubs = new PageImpl<ClubModel>(Arrays.asList(new ClubModel()));
		when(clubServiceMock.getAll(any(), any(), any(), any())).thenReturn(expectedClubs);

		mockMvc.perform(MockMvcRequestBuilders.get("/clubs")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$.content", hasSize(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

}
