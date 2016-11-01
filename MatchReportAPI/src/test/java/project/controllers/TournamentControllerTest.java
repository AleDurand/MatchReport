package project.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import project.Application;
import project.exceptions.EntityNotFoundException;
import project.models.TournamentModel;
import project.services.TournamentService;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class TournamentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GlobalExceptionController controllerAdvice;

	@InjectMocks
	private TournamentController tournamentController;

	@Mock
	private TournamentService tournamentServiceMock;

	@Autowired
	private ObjectMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(tournamentController)
				.setControllerAdvice(controllerAdvice).build();
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
		TournamentModel tournament = new TournamentModel();
		tournament.setId(1);
		tournament.setInitDate(null);
		tournament.setEndDate(null);
		
		TournamentModel expectedTournament = new TournamentModel();
		expectedTournament.setId(1);
		expectedTournament.setInitDate(null);
		expectedTournament.setEndDate(null);
		when(tournamentServiceMock.create(any())).thenReturn(expectedTournament);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/tournaments")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsBytes(tournament))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		TournamentModel expectedTournament = new TournamentModel();
		expectedTournament.setId(1);
		expectedTournament.setInitDate(null);
		expectedTournament.setEndDate(null);
		when(tournamentServiceMock.read(1)).thenReturn(expectedTournament);

		mockMvc.perform(MockMvcRequestBuilders.get("/tournaments/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void readNotFound() throws Exception {
		// @formatter:off
		when(tournamentServiceMock.read(1)).thenThrow(new EntityNotFoundException("resource.not_found", null));

		mockMvc.perform(MockMvcRequestBuilders.get("/tournaments/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.delete("/tournaments/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
		// @formatter:on
	}

	@Test
	public void deleteNotFound() throws Exception {
		// @formatter:off
		doThrow(new EntityNotFoundException("resource.not_found", null)).when(tournamentServiceMock).delete(1);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/tournaments/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		List<TournamentModel> expectedTournaments = Arrays.asList(new TournamentModel());
		when(tournamentServiceMock.getAll(null, null, null, null, null)).thenReturn(expectedTournaments);

		mockMvc.perform(MockMvcRequestBuilders.get("/tournaments")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}
}
