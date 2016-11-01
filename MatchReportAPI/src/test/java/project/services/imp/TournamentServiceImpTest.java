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
import project.models.QTournamentModel;
import project.models.RoundModel;
import project.models.TournamentModel;
import project.repositories.RoundRepository;
import project.repositories.TournamentRepository;
import project.services.TournamentService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class TournamentServiceImpTest {

	@InjectMocks
	@Autowired
	private TournamentService tournamentService;

	@Mock
	private TournamentRepository tournamentRepositoryMock;
	
	@Mock
	private RoundRepository roundRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void create() throws Exception {
		// @formatter:off
		TournamentModel expectedTournament = new TournamentModel();
		expectedTournament.setId(1);
		expectedTournament.setInitDate(null);
		expectedTournament.setEndDate(null);
		when(tournamentRepositoryMock.save(expectedTournament)).thenReturn(expectedTournament);

		TournamentModel actualTournament = tournamentService.create(expectedTournament);

		assertEquals(expectedTournament.getId(), actualTournament.getId());
		assertEquals(expectedTournament.getInitDate(), actualTournament.getInitDate());
		assertEquals(expectedTournament.getEndDate(), actualTournament.getEndDate());
		// @formatter:on
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		TournamentModel expectedTournament = new TournamentModel();
		expectedTournament.setInitDate(null);
		expectedTournament.setEndDate(null);
		when(tournamentRepositoryMock.exists(1)).thenReturn(true);
		when(tournamentRepositoryMock.findOne(1)).thenReturn(expectedTournament);

		TournamentModel actualTournament = tournamentService.read(1);

		assertEquals(expectedTournament.getId(), actualTournament.getId());
		assertEquals(expectedTournament.getInitDate(), actualTournament.getInitDate());
		assertEquals(expectedTournament.getEndDate(), actualTournament.getEndDate());
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void readNotFound() throws Exception {
		// @formatter:off
		when(tournamentRepositoryMock.exists(1)).thenReturn(false);

		tournamentService.read(1);
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		when(tournamentRepositoryMock.exists(1)).thenReturn(true);

		tournamentService.delete(1);
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteNotFound() throws Exception {
		// @formatter:off
		when(tournamentRepositoryMock.exists(1)).thenReturn(false);

		tournamentService.delete(1);
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		List<TournamentModel> expectedTournaments = Arrays.asList(new TournamentModel());
		QTournamentModel tournament = QTournamentModel.tournamentModel;
		when(tournamentRepositoryMock.findAll(tournament.instanceOfAny())).thenReturn(expectedTournaments);

		List<TournamentModel> actualTournaments = tournamentService.getAll(null, null, null, null, null);

		assertEquals(expectedTournaments.size(), actualTournaments.size());
		assertEquals(expectedTournaments, actualTournaments);
		// @formatter:on
	}
	
	@Test
	public void createRound() throws Exception {
		// @formatter:off
		RoundModel expectedRound = new RoundModel();
		expectedRound.setId(1);
		expectedRound.setNumber(1);
		expectedRound.setDescription("description");
		expectedRound.setTournament(null);
		when(tournamentRepositoryMock.exists(1)).thenReturn(true);
		when(roundRepositoryMock.save(expectedRound)).thenReturn(expectedRound);

		RoundModel actualRound = tournamentService.addRound(1, expectedRound);

		assertEquals(expectedRound.getId(), actualRound.getId());
		assertEquals(expectedRound.getNumber(), actualRound.getNumber());
		assertEquals(expectedRound.getDescription(), actualRound.getDescription());
		assertEquals(expectedRound.getTournament(), actualRound.getTournament());
		// @formatter:on
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void createRoundTournamentNotFound() throws Exception {
		// @formatter:off
		when(tournamentRepositoryMock.exists(1)).thenReturn(false);

		tournamentService.addRound(1, new RoundModel());
		// @formatter:on
	}
	
	@Test
	public void getAllRounds() throws Exception {
		// @formatter:off
		List<RoundModel> expectedRounds = Arrays.asList(new RoundModel());
		when(tournamentRepositoryMock.exists(1)).thenReturn(true);
		when(roundRepositoryMock.findByTournamentId(1)).thenReturn(expectedRounds);

		List<RoundModel> actualRounds = tournamentService.getAllRounds(1);

		assertEquals(expectedRounds.size(), actualRounds.size());
		assertEquals(expectedRounds, actualRounds);
		// @formatter:on
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void getAllRoundsTournamentNotFound() throws Exception {
		// @formatter:off
		when(tournamentRepositoryMock.exists(1)).thenReturn(false);

		tournamentService.getAllRounds(1);
		// @formatter:on
	}
	
}
