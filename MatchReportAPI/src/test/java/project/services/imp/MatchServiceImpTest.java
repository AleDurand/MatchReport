package project.services.imp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import project.Application;
import project.exceptions.EntityNotFoundException;
import project.models.MatchModel;
import project.repositories.MatchRepository;
import project.services.MatchService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class MatchServiceImpTest {

	@InjectMocks
	@Autowired
	private MatchService matchService;

	@Mock
	private MatchRepository matchRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		MatchModel expectedMatch = new MatchModel();
		expectedMatch.setId(1);
		expectedMatch.setDate(null);
		expectedMatch.setHour(null);
		expectedMatch.setStatus("status");
		expectedMatch.setStadium(null);
		expectedMatch.setLocal(null);
		expectedMatch.setVisitor(null);
		when(matchRepositoryMock.exists(1)).thenReturn(true);
		when(matchRepositoryMock.findOne(1)).thenReturn(expectedMatch);

		MatchModel actualMatch = matchService.read(1);

		assertEquals(expectedMatch.getId(), actualMatch.getId());
		assertEquals(expectedMatch.getDate(), actualMatch.getDate());
		assertEquals(expectedMatch.getHour(), actualMatch.getHour());
		assertEquals(expectedMatch.getStatus(), actualMatch.getStatus());
		assertEquals(expectedMatch.getStadium(), actualMatch.getStadium());
		assertEquals(expectedMatch.getLocal(), actualMatch.getLocal());
		assertEquals(expectedMatch.getVisitor(), actualMatch.getVisitor());
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void readNotFound() throws Exception {
		// @formatter:off
		when(matchRepositoryMock.exists(1)).thenReturn(false);

		matchService.read(1);
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		when(matchRepositoryMock.exists(1)).thenReturn(true);

		matchService.delete(1);
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteNotFound() throws Exception {
		// @formatter:off
		when(matchRepositoryMock.exists(1)).thenReturn(false);

		matchService.delete(1);
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		List<MatchModel> expectedMatches = Arrays.asList(new MatchModel());
		when(matchRepositoryMock.findAll()).thenReturn(expectedMatches);

		List<MatchModel> actualMatches = matchService.getAll();

		assertEquals(expectedMatches.size(), actualMatches.size());
		assertEquals(expectedMatches, actualMatches);
		// @formatter:on
	}
}
