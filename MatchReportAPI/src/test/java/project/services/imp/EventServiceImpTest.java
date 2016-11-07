package project.services.imp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import project.Application;
import project.models.EventModel;
import project.repositories.EventRepository;
import project.services.EventService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class EventServiceImpTest {

	@InjectMocks
	@Autowired
	private EventService eventService;

	@Mock
	private EventRepository eventRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAll() throws Exception {
		// @formatter:off
		Page<EventModel> expectedEvents = new PageImpl<EventModel>(Arrays.asList(new EventModel()));
		Pageable pageable = new PageRequest(0, 1000);		
		when(eventRepositoryMock.findAll(pageable)).thenReturn(expectedEvents);

		Page<EventModel> actualEvents = eventService.getAll(pageable);

		assertEquals(expectedEvents.getTotalElements(), actualEvents.getTotalElements());
		assertEquals(expectedEvents, actualEvents);
		// @formatter:on
	}

}
