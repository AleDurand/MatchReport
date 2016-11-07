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
import project.models.EventModel;
import project.services.MatchService;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class MatchEventControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GlobalExceptionController controllerAdvice;

	@InjectMocks
	private MatchEventController matchEventController;

	@Mock
	private MatchService matchServiceMock;

	@Autowired
	private ObjectMapper mapper;
		
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(matchEventController)
				.setControllerAdvice(controllerAdvice).build();
	}

	@Test
	public void getAllMatches() throws Exception {
		// @formatter:off
		List<EventModel> expectedEvents = Arrays.asList(new EventModel());
		when(matchServiceMock.getAllEvents(1)).thenReturn(expectedEvents);

		mockMvc.perform(MockMvcRequestBuilders.get("/matches/{id}/events", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

}
