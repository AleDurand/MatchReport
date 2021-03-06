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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import project.Application;
import project.exceptions.EntityNotFoundException;
import project.models.MatchModel;
import project.models.MatchStatus;
import project.models.QRoundModel;
import project.models.RoundModel;
import project.repositories.MatchRepository;
import project.repositories.RoundRepository;
import project.services.RoundService;

import com.querydsl.core.types.dsl.BooleanExpression;

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
		Page<RoundModel> expectedRounds = new PageImpl<RoundModel>(Arrays.asList(new RoundModel()));
		Pageable pageable = new PageRequest(0, 1000);
		QRoundModel round = QRoundModel.roundModel;
		when(roundRepositoryMock.findAll(round.instanceOfAny(), pageable)).thenReturn(expectedRounds);

		Page<RoundModel> actualRounds = roundService.getAll(null, null, null, null, pageable);

		assertEquals(expectedRounds.getTotalElements(), actualRounds.getTotalElements());
		assertEquals(expectedRounds, actualRounds);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredById() throws Exception {
		// @formatter:off
		Page<RoundModel> expectedRounds = new PageImpl<RoundModel>(Arrays.asList(new RoundModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QRoundModel.roundModel.id.eq(1);	
			
		when(roundRepositoryMock.findAll(expression, pageable)).thenReturn(expectedRounds);

		Page<RoundModel> actualRounds = roundService.getAll(1, null, null, null, pageable);
		
		assertEquals(expectedRounds.getTotalElements(), actualRounds.getTotalElements());
		assertEquals(expectedRounds, actualRounds);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByNumber() throws Exception {
		// @formatter:off
		Page<RoundModel> expectedRounds = new PageImpl<RoundModel>(Arrays.asList(new RoundModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QRoundModel.roundModel.number.eq(1);	
			
		when(roundRepositoryMock.findAll(expression, pageable)).thenReturn(expectedRounds);

		Page<RoundModel> actualRounds = roundService.getAll(null, 1, null, null, pageable);
		
		assertEquals(expectedRounds.getTotalElements(), actualRounds.getTotalElements());
		assertEquals(expectedRounds, actualRounds);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByDescription() throws Exception {
		// @formatter:off
		Page<RoundModel> expectedRounds = new PageImpl<RoundModel>(Arrays.asList(new RoundModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QRoundModel.roundModel.description.contains("description");	
			
		when(roundRepositoryMock.findAll(expression, pageable)).thenReturn(expectedRounds);

		Page<RoundModel> actualRounds = roundService.getAll(null, null, "description", null, pageable);
		
		assertEquals(expectedRounds.getTotalElements(), actualRounds.getTotalElements());
		assertEquals(expectedRounds, actualRounds);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByIdTournament() throws Exception {
		// @formatter:off
		Page<RoundModel> expectedRounds = new PageImpl<RoundModel>(Arrays.asList(new RoundModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QRoundModel.roundModel.tournament.id.eq(1);	
			
		when(roundRepositoryMock.findAll(expression, pageable)).thenReturn(expectedRounds);

		Page<RoundModel> actualRounds = roundService.getAll(null, null, null, 1, pageable);
		
		assertEquals(expectedRounds.getTotalElements(), actualRounds.getTotalElements());
		assertEquals(expectedRounds, actualRounds);
		// @formatter:on
	}

	@Test
	public void createMatch() throws Exception {
		// @formatter:off
		MatchModel expectedMatch = new MatchModel();
		expectedMatch.setId(1);
		expectedMatch.setDate(null);
		expectedMatch.setStatus(MatchStatus.IN_PROGRESS);
		expectedMatch.setStadium(null);
		expectedMatch.setLocal(null);
		expectedMatch.setVisitor(null);
		when(roundRepositoryMock.exists(1)).thenReturn(true);
		when(matchRepositoryMock.save(expectedMatch)).thenReturn(expectedMatch);

		MatchModel actualMatch = roundService.addMatch(1, expectedMatch);

		assertEquals(expectedMatch.getId(), actualMatch.getId());
		assertEquals(expectedMatch.getDate(), actualMatch.getDate());
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
