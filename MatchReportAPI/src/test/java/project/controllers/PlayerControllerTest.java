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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import project.Application;
import project.exceptions.EntityNotFoundException;
import project.models.PlayerModel;
import project.models.PlayerStatus;
import project.services.PlayerService;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class PlayerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GlobalExceptionController controllerAdvice;
	
	@Autowired
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@InjectMocks
	private PlayerController playerController;

	@Mock
	private PlayerService playerServiceMock;
	
	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(playerController)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(controllerAdvice).build();
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
		PlayerModel player = new PlayerModel();
		player.setId(1);
		player.setFirstname("firstname");
		player.setLastname("lastname");
		player.setDocumentType("DNI");
		player.setDocumentNumber(10000000);
		player.setStatus(PlayerStatus.ENABLED);
		
		PlayerModel expectedPlayer = new PlayerModel();
		expectedPlayer.setId(1);
		expectedPlayer.setFirstname("firstname");
		expectedPlayer.setLastname("lastname");
		expectedPlayer.setDocumentType("DNI");
		expectedPlayer.setDocumentNumber(10000000);
		expectedPlayer.setStatus(PlayerStatus.ENABLED);
		when(playerServiceMock.create(any())).thenReturn(expectedPlayer);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/players")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsBytes(player))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.firstname", is("firstname")))
			.andExpect(jsonPath("$.lastname", is("lastname")))
			.andExpect(jsonPath("$.documentType", is("DNI")))
			.andExpect(jsonPath("$.documentNumber", is(10000000)))
			.andExpect(jsonPath("$.status", is("ENABLED")))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		PlayerModel expectedPlayer = new PlayerModel();
		expectedPlayer.setId(1);
		expectedPlayer.setFirstname("firstname");
		expectedPlayer.setLastname("lastname");
		expectedPlayer.setDocumentType("DNI");
		expectedPlayer.setDocumentNumber(10000000);
		expectedPlayer.setStatus(PlayerStatus.ENABLED);
		when(playerServiceMock.read(1)).thenReturn(expectedPlayer);

		mockMvc.perform(MockMvcRequestBuilders.get("/players/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.firstname", is("firstname")))
			.andExpect(jsonPath("$.lastname", is("lastname")))
			.andExpect(jsonPath("$.documentType", is("DNI")))
			.andExpect(jsonPath("$.documentNumber", is(10000000)))
			.andExpect(jsonPath("$.status", is("ENABLED")))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void readNotFound() throws Exception {
		// @formatter:off
		when(playerServiceMock.read(1)).thenThrow(new EntityNotFoundException("resource.not_found", null));

		mockMvc.perform(MockMvcRequestBuilders.get("/players/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.delete("/players/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
		// @formatter:on
	}

	@Test
	public void deleteNotFound() throws Exception {
		// @formatter:off
		doThrow(new EntityNotFoundException("resource.not_found", null)).when(playerServiceMock).delete(1);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/players/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		Page<PlayerModel> expectedPlayers = new PageImpl<PlayerModel>(Arrays.asList(new PlayerModel()));
		when(playerServiceMock.getAll(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(expectedPlayers);

		mockMvc.perform(MockMvcRequestBuilders.get("/players")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$.content", hasSize(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

}
