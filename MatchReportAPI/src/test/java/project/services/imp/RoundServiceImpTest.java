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
import project.models.RoundModel;
import project.repositories.MatchRepository;
import project.repositories.RoundRepository;
import project.services.RoundService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class RoundServiceImpTest {

	@InjectMocks
	@Autowired
	private RoundService roundService;

	@Mock
	private RoundRepository roundRepositoryMock;

	@Mock
	private MatchRepository matchRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		RoundModel expectedRound = new RoundModel();
		expectedRound.setId(1);
		expectedRound.setNumber(1);
		expectedRound.setDescription("description");
		expectedRound.setTournament(null);
		when(roundRepositoryMock.exists(1)).thenReturn(true);
		when(roundRepositoryMock.findOne(1)).thenReturn(expectedRound);

		RoundModel actualRound = roundService.read(1);

		assertEquals(expectedRound.getId(), actualRound.getId());
		assertEquals(expectedRound.getNumber(), actualRound.getNumber());
		assertEquals(expectedRound.getDescription(), actualRound.getDescription());
		assertEquals(expectedRound.getTournament(), actualRound.getTournament());
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void readNotFound() throws Exception {
		// @formatter:off
		when(roundRepositoryMock.exists(1)).thenReturn(false);

		roundService.read(1);
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		when(roundRepositoryMock.exists(1)).thenReturn(true);

		roundService.delete(1);
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteNotFound() throws Exception {
		// @formatter:off
		when(roundRepositoryMock.exists(1)).thenReturn(false);

		roundService.delete(1);
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		List<RoundModel> expectedRounds = Arrays.asList(new RoundModel());
		when(roundRepositoryMock.findAll()).thenReturn(expectedRounds);

		List<RoundModel> actualRounds = roundService.getAll();

		assertEquals(expectedRounds.size(), actualRounds.size());
		assertEquals(expectedRounds, actualRounds);
		// @formatter:on
	}

	@Test
	public void createMatch() throws Exception {
		// @formatter:off
		MatchModel expectedMatch = new MatchModel();
		expectedMatch.setId(1);
		expectedMatch.setDate(null);
		expectedMatch.setHour(null);
		expectedMatch.setStatus(0);
		expectedMatch.setStadium(null);
		expectedMatch.setLocal(null);
		expectedMatch.setVisitor(null);
		when(roundRepositoryMock.exists(1)).thenReturn(true);
		when(matchRepositoryMock.save(expectedMatch)).thenReturn(expectedMatch);

		MatchModel actualMatch = roundService.addMatch(1, expectedMatch);

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
	public void createMatchRoundNotFound() throws Exception {
		// @formatter:off
		when(roundRepositoryMock.exists(1)).thenReturn(false);

		roundService.addMatch(1, new MatchModel());
		// @formatter:on
	}

	@Test
	public void getAllMatches() throws Exception {
		// @formatter:off
		List<MatchModel> expectedMatches = Arrays.asList(new MatchModel());
		when(roundRepositoryMock.exists(1)).thenReturn(true);
		when(matchRepositoryMock.findByRoundId(1)).thenReturn(expectedMatches);

		List<MatchModel> actualMatches = roundService.getAllMatches(1);

		assertEquals(expectedMatches.size(), actualMatches.size());
		assertEquals(expectedMatches, actualMatches);
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void getAllMatchesRoundNotFound() throws Exception {
		// @formatter:off
		when(roundRepositoryMock.exists(1)).thenReturn(false);

		roundService.getAllMatches(1);
		// @formatter:on
	}
}
