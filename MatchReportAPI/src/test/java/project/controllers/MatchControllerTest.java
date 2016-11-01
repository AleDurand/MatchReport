package project.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
import project.models.MatchModel;
import project.models.MatchStatus;
import project.services.MatchService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class MatchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GlobalExceptionController controllerAdvice;

	@InjectMocks
	private MatchController matchController;

	@Mock
	private MatchService matchServiceMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(matchController)
				.setControllerAdvice(controllerAdvice).build();
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		MatchModel expectedMatch = new MatchModel();
		expectedMatch.setId(1);		
		expectedMatch.setStatus(MatchStatus.PENDING);
		expectedMatch.setStadium(null);
		
		when(matchServiceMock.read(1)).thenReturn(expectedMatch);

		mockMvc.perform(MockMvcRequestBuilders.get("/matches/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.status", is("PENDING")))
			.andExpect(jsonPath("$.stadium").doesNotExist())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void readNotFound() throws Exception {
		// @formatter:off
		when(matchServiceMock.read(1)).thenThrow(new EntityNotFoundException("resource.not_found", null));

		mockMvc.perform(MockMvcRequestBuilders.get("/matches/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.delete("/matches/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
		// @formatter:on
	}

	@Test
	public void deleteNotFound() throws Exception {
		// @formatter:off
		doThrow(new EntityNotFoundException("resource.not_found", null)).when(matchServiceMock).delete(1);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/matches/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		List<MatchModel> expectedMatches = Arrays.asList(new MatchModel());
		when(matchServiceMock.getAll(null, null, null, null, null, null, null, null)).thenReturn(expectedMatches);

		mockMvc.perform(MockMvcRequestBuilders.get("/matches")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}
}
