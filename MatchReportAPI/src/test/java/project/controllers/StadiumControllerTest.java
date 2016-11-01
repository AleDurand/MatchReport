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
import project.models.StadiumModel;
import project.services.StadiumService;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class StadiumControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GlobalExceptionController controllerAdvice;

	@InjectMocks
	private StadiumController stadiumController;

	@Mock
	private StadiumService stadiumServiceMock;

	@Autowired
	private ObjectMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(stadiumController)
				.setControllerAdvice(controllerAdvice).build();
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
		StadiumModel stadium = new StadiumModel();
		stadium.setName("name");
		stadium.setAddress("address");
		stadium.setImage("image");
		
		StadiumModel expectedStadium = new StadiumModel();
		expectedStadium.setId(1);
		expectedStadium.setName("name");
		expectedStadium.setAddress("address");
		expectedStadium.setImage("image");
		when(stadiumServiceMock.create(any())).thenReturn(expectedStadium);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/stadiums")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsBytes(stadium))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("name")))
			.andExpect(jsonPath("$.address", is("address")))
			.andExpect(jsonPath("$.image", is("image")))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}
	
	@Test
	public void read() throws Exception {
		// @formatter:off
		StadiumModel expectedStadium = new StadiumModel();
		expectedStadium.setId(1);
		expectedStadium.setName("name");
		expectedStadium.setAddress("address");
		expectedStadium.setImage("image");
		when(stadiumServiceMock.read(1)).thenReturn(expectedStadium);

		mockMvc.perform(MockMvcRequestBuilders.get("/stadiums/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("name")))
			.andExpect(jsonPath("$.address", is("address")))
			.andExpect(jsonPath("$.image", is("image")))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void readNotFound() throws Exception {
		// @formatter:off
		when(stadiumServiceMock.read(1)).thenThrow(new EntityNotFoundException("resource.not_found", null));

		mockMvc.perform(MockMvcRequestBuilders.get("/stadiums/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.delete("/stadiums/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
		// @formatter:on
	}

	@Test
	public void deleteNotFound() throws Exception {
		// @formatter:off
		doThrow(new EntityNotFoundException("resource.not_found", null)).when(stadiumServiceMock).delete(1);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/stadiums/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		List<StadiumModel> expectedStadiums = Arrays.asList(new StadiumModel());
		when(stadiumServiceMock.getAll(null, null)).thenReturn(expectedStadiums);

		mockMvc.perform(MockMvcRequestBuilders.get("/stadiums")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}
}
