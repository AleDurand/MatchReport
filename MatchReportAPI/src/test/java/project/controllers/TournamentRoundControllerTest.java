package project.controllers;

import static org.hamcrest.Matchers.hasSize;
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
import project.models.RoundModel;
import project.services.TournamentService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class TournamentRoundControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GlobalExceptionController controllerAdvice;

	@InjectMocks
	private TournamentRoundController tournamentRoundController;

	@Mock
	private TournamentService tournamentServiceMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(tournamentRoundController)
				.setControllerAdvice(controllerAdvice).build();
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
//			mockMvc.perform(MockMvcRequestBuilders.post("/clubs")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content("{\"name\":\"name\", \"address\":\"address\", \"url\": \"url\"}")
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated())
//				.andExpect(jsonPath("$.id").exists())
//				.andExpect(jsonPath("$.name", is("name")))
//				.andExpect(jsonPath("$.address", is("address")))
//				.andExpect(jsonPath("$.stadium").doesNotExist())
//				.andExpect(jsonPath("$.url", is("url")))
//				.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}
	
	@Test
	public void getAllRounds() throws Exception {
		// @formatter:off
		List<RoundModel> expectedRounds = Arrays.asList(new RoundModel());
		when(tournamentServiceMock.getAllRounds(1)).thenReturn(expectedRounds);

		mockMvc.perform(MockMvcRequestBuilders.get("/tournaments/{id}/rounds", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}
}
