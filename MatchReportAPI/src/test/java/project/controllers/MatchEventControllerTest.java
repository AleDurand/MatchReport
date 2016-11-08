package project.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import project.Application;
import project.models.EventModel;
import project.models.EventType;
import project.models.MatchModel;
import project.services.MatchService;
import project.validators.CompositeValidator;

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

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@InjectMocks
	private MatchEventController matchEventController;

	@Mock
	private MatchService matchServiceMock;
	
	@Mock
	private CompositeValidator compositeValidatorMock;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(matchEventController)
				.setControllerAdvice(controllerAdvice)
				.apply(springSecurity(springSecurityFilterChain)).build();
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void create() throws Exception {
		// @formatter:off
		EventModel event = new EventModel();	
		event.setMinute(1);
		event.setExtraMinute(null);
		event.setType(EventType.START_PERIOD);
		event.setMatch(new MatchModel());
		
		EventModel expectedEvent = new EventModel();
		expectedEvent.setId(1);
		expectedEvent.setMinute(1);
		expectedEvent.setExtraMinute(null);
		expectedEvent.setType(EventType.START_PERIOD);
		expectedEvent.setMatch(new MatchModel());
		when(compositeValidatorMock.supports(any())).thenReturn(true);
		when(matchServiceMock.addEvent(any(), any())).thenReturn(expectedEvent);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/matches/{id}/events", 1)
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsBytes(event))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.minute", is(1)))
			.andExpect(jsonPath("$.extraMinute").doesNotExist())
			.andExpect(jsonPath("$.type", is("START_PERIOD")))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}
	
	@Test
	public void getAllEvents() throws Exception {
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
