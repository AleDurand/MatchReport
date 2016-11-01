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
import project.exceptions.EntityNotFoundException;
import project.models.MatchModel;
import project.models.MatchStatus;
import project.models.QMatchModel;
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
		expectedMatch.setStatus(MatchStatus.IN_PROGRESS);
		expectedMatch.setStadium(null);
		expectedMatch.setLocal(null);
		expectedMatch.setVisitor(null);
		when(matchRepositoryMock.exists(1)).thenReturn(true);
		when(matchRepositoryMock.findOne(1)).thenReturn(expectedMatch);

		MatchModel actualMatch = matchService.read(1);

		assertEquals(expectedMatch.getId(), actualMatch.getId());
		assertEquals(expectedMatch.getDate(), actualMatch.getDate());
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
		Page<MatchModel> expectedMatches = new PageImpl<MatchModel>(Arrays.asList(new MatchModel()));
		Pageable pageable = new PageRequest(0, 1000);
		QMatchModel match = QMatchModel.matchModel;		
		when(matchRepositoryMock.findAll(match.instanceOfAny(), pageable)).thenReturn(expectedMatches);

		Page<MatchModel> actualMatches = matchService.getAll(null, null, null, null, null, null, null, null, pageable);

		assertEquals(expectedMatches.getTotalElements(), actualMatches.getTotalElements());
		assertEquals(expectedMatches, actualMatches);
		// @formatter:on
	}
}
