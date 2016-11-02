package project.services.imp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.querydsl.core.types.dsl.BooleanExpression;

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
		Page<TournamentModel> expectedTournaments = new PageImpl<TournamentModel>(Arrays.asList(new TournamentModel()));
		Pageable pageable = new PageRequest(0, 1000);
		QTournamentModel tournament = QTournamentModel.tournamentModel;
		when(tournamentRepositoryMock.findAll(tournament.instanceOfAny(), pageable)).thenReturn(expectedTournaments);

		Page<TournamentModel> actualTournaments = tournamentService.getAll(null, null, null, null, null, pageable);

		assertEquals(expectedTournaments.getTotalElements(), actualTournaments.getTotalElements());
		assertEquals(expectedTournaments, actualTournaments);
		// @formatter:on
	}

	@Test
	public void getAllFilteredById() throws Exception {
		// @formatter:off
		Page<TournamentModel> expectedTournaments = new PageImpl<TournamentModel>(Arrays.asList(new TournamentModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QTournamentModel.tournamentModel.id.eq(1);	
			
		when(tournamentRepositoryMock.findAll(expression, pageable)).thenReturn(expectedTournaments);

		Page<TournamentModel> actualTournaments = tournamentService.getAll(1,  null, null, null, null, pageable);
		
		assertEquals(expectedTournaments.getTotalElements(), actualTournaments.getTotalElements());
		assertEquals(expectedTournaments, actualTournaments);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByInitDateBefore() throws Exception {
		// @formatter:off
		Page<TournamentModel> expectedTournaments = new PageImpl<TournamentModel>(Arrays.asList(new TournamentModel()));
		Pageable pageable = new PageRequest(0, 1000);
		Date date = new Date();
		BooleanExpression expression = QTournamentModel.tournamentModel.initDate.before(date);	
			
		when(tournamentRepositoryMock.findAll(expression, pageable)).thenReturn(expectedTournaments);

		Page<TournamentModel> actualTournaments = tournamentService.getAll(null,  date, null, null, null, pageable);
		
		assertEquals(expectedTournaments.getTotalElements(), actualTournaments.getTotalElements());
		assertEquals(expectedTournaments, actualTournaments);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByInitDateAfter() throws Exception {
		// @formatter:off
		Page<TournamentModel> expectedTournaments = new PageImpl<TournamentModel>(Arrays.asList(new TournamentModel()));
		Pageable pageable = new PageRequest(0, 1000);
		Date date = new Date();
		BooleanExpression expression = QTournamentModel.tournamentModel.initDate.after(date);	
			
		when(tournamentRepositoryMock.findAll(expression, pageable)).thenReturn(expectedTournaments);

		Page<TournamentModel> actualTournaments = tournamentService.getAll(null,  null, date, null, null, pageable);
		
		assertEquals(expectedTournaments.getTotalElements(), actualTournaments.getTotalElements());
		assertEquals(expectedTournaments, actualTournaments);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByEndDateBefore() throws Exception {
		// @formatter:off
		Page<TournamentModel> expectedTournaments = new PageImpl<TournamentModel>(Arrays.asList(new TournamentModel()));
		Pageable pageable = new PageRequest(0, 1000);
		Date date = new Date();
		BooleanExpression expression = QTournamentModel.tournamentModel.endDate.before(date);	
			
		when(tournamentRepositoryMock.findAll(expression, pageable)).thenReturn(expectedTournaments);

		Page<TournamentModel> actualTournaments = tournamentService.getAll(null,  null, null, date, null, pageable);
		
		assertEquals(expectedTournaments.getTotalElements(), actualTournaments.getTotalElements());
		assertEquals(expectedTournaments, actualTournaments);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByEndDateAfter() throws Exception {
		// @formatter:off
		Page<TournamentModel> expectedTournaments = new PageImpl<TournamentModel>(Arrays.asList(new TournamentModel()));
		Pageable pageable = new PageRequest(0, 1000);
		Date date = new Date();
		BooleanExpression expression = QTournamentModel.tournamentModel.endDate.after(date);	
			
		when(tournamentRepositoryMock.findAll(expression, pageable)).thenReturn(expectedTournaments);

		Page<TournamentModel> actualTournaments = tournamentService.getAll(null,  null, null, null, date, pageable);
		
		assertEquals(expectedTournaments.getTotalElements(), actualTournaments.getTotalElements());
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
