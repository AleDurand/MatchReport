/*package project.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import project.MockApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MockApplication.class })
@AutoConfigureMockMvc
public class ClubControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getById() throws Exception {
		//@formatter:off
		mockMvc.perform(MockMvcRequestBuilders.get("/clubs/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
		//@formatter:on
	}

}
*/