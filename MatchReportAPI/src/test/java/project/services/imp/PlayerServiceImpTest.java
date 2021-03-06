package project.services.imp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

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
import project.models.PlayerModel;
import project.models.PlayerStatus;
import project.models.QPlayerModel;
import project.repositories.PlayerRepository;
import project.services.PlayerService;

import com.querydsl.core.types.dsl.BooleanExpression;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class PlayerServiceImpTest {

	@InjectMocks
	@Autowired
	private PlayerService playerService;

	@Mock
	private PlayerRepository playerRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
		PlayerModel expectedPlayer = new PlayerModel();
		expectedPlayer.setId(1);
		expectedPlayer.setFirstname("firstname");
		expectedPlayer.setLastname("lastname");
		expectedPlayer.setDocumentType("DNI");
		expectedPlayer.setDocumentNumber(10000000);
		expectedPlayer.setStatus(PlayerStatus.ENABLED);
		when(playerRepositoryMock.save(expectedPlayer)).thenReturn(expectedPlayer);

		PlayerModel actualPlayer = playerService.create(expectedPlayer);

		assertEquals(expectedPlayer.getId(), actualPlayer.getId());
		assertEquals(expectedPlayer.getFirstname(), actualPlayer.getFirstname());
		assertEquals(expectedPlayer.getLastname(), actualPlayer.getLastname());
		assertEquals(expectedPlayer.getDocumentType(), actualPlayer.getDocumentType());
		assertEquals(expectedPlayer.getDocumentNumber(), actualPlayer.getDocumentNumber());
		assertEquals(expectedPlayer.getStatus(), actualPlayer.getStatus());
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
		when(playerRepositoryMock.exists(1)).thenReturn(true);
		when(playerRepositoryMock.findOne(1)).thenReturn(expectedPlayer);

		PlayerModel actualPlayer = playerService.read(1);

		assertEquals(expectedPlayer.getId(), actualPlayer.getId());
		assertEquals(expectedPlayer.getFirstname(), actualPlayer.getFirstname());
		assertEquals(expectedPlayer.getLastname(), actualPlayer.getLastname());
		assertEquals(expectedPlayer.getDocumentType(), actualPlayer.getDocumentType());
		assertEquals(expectedPlayer.getDocumentNumber(), actualPlayer.getDocumentNumber());
		assertEquals(expectedPlayer.getStatus(), actualPlayer.getStatus());
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void readNotFound() throws Exception {
		// @formatter:off
		when(playerRepositoryMock.exists(1)).thenReturn(false);

		playerService.read(1);
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		when(playerRepositoryMock.exists(1)).thenReturn(true);

		playerService.delete(1);
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteNotFound() throws Exception {
		// @formatter:off
		when(playerRepositoryMock.exists(1)).thenReturn(false);

		playerService.delete(1);
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		Page<PlayerModel> expectedPlayers = new PageImpl<PlayerModel>(Arrays.asList(new PlayerModel()));
		Pageable pageable = new PageRequest(0, 1000);
		QPlayerModel player = QPlayerModel.playerModel;
		when(playerRepositoryMock.findAll(player.instanceOfAny(), pageable)).thenReturn(expectedPlayers);

		Page<PlayerModel> actualPlayers = playerService.getAll(null, null, null, null, null, null, null, pageable);

		assertEquals(expectedPlayers.getTotalElements(), actualPlayers.getTotalElements());
		assertEquals(expectedPlayers, actualPlayers);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredById() throws Exception {
		// @formatter:off
		Page<PlayerModel> expectedPlayers = new PageImpl<PlayerModel>(Arrays.asList(new PlayerModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QPlayerModel.playerModel.id.eq(1);	
			
		when(playerRepositoryMock.findAll(expression, pageable)).thenReturn(expectedPlayers);

		Page<PlayerModel> actualPlayers = playerService.getAll(1, null, null, null, null, null, null, pageable);
		
		assertEquals(expectedPlayers.getTotalElements(), actualPlayers.getTotalElements());
		assertEquals(expectedPlayers, actualPlayers);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByFirstname() throws Exception {
		// @formatter:off
		Page<PlayerModel> expectedPlayers = new PageImpl<PlayerModel>(Arrays.asList(new PlayerModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QPlayerModel.playerModel.firstname.contains("firstname");	
			
		when(playerRepositoryMock.findAll(expression, pageable)).thenReturn(expectedPlayers);

		Page<PlayerModel> actualPlayers = playerService.getAll(null, "firstname", null, null, null, null, null, pageable);
		
		assertEquals(expectedPlayers.getTotalElements(), actualPlayers.getTotalElements());
		assertEquals(expectedPlayers, actualPlayers);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByLastname() throws Exception {
		// @formatter:off
		Page<PlayerModel> expectedPlayers = new PageImpl<PlayerModel>(Arrays.asList(new PlayerModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QPlayerModel.playerModel.lastname.contains("lastname");	
			
		when(playerRepositoryMock.findAll(expression, pageable)).thenReturn(expectedPlayers);

		Page<PlayerModel> actualPlayers = playerService.getAll(null, null, "lastname", null, null, null, null, pageable);
		
		assertEquals(expectedPlayers.getTotalElements(), actualPlayers.getTotalElements());
		assertEquals(expectedPlayers, actualPlayers);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByBirthDateBefore() throws Exception {
		// @formatter:off
		Page<PlayerModel> expectedPlayers = new PageImpl<PlayerModel>(Arrays.asList(new PlayerModel()));
		Pageable pageable = new PageRequest(0, 1000);
		Date date = new Date();
		BooleanExpression expression = QPlayerModel.playerModel.birthDate.before(date);	
			
		when(playerRepositoryMock.findAll(expression, pageable)).thenReturn(expectedPlayers);

		Page<PlayerModel> actualPlayers = playerService.getAll(null, null, null, date, null, null, null, pageable);
		
		assertEquals(expectedPlayers.getTotalElements(), actualPlayers.getTotalElements());
		assertEquals(expectedPlayers, actualPlayers);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByBirthDateAfter() throws Exception {
		// @formatter:off
		Page<PlayerModel> expectedPlayers = new PageImpl<PlayerModel>(Arrays.asList(new PlayerModel()));
		Pageable pageable = new PageRequest(0, 1000);
		Date date = new Date();
		BooleanExpression expression = QPlayerModel.playerModel.birthDate.after(date);	
			
		when(playerRepositoryMock.findAll(expression, pageable)).thenReturn(expectedPlayers);

		Page<PlayerModel> actualPlayers = playerService.getAll(null, null, null, null, date, null, null, pageable);
		
		assertEquals(expectedPlayers.getTotalElements(), actualPlayers.getTotalElements());
		assertEquals(expectedPlayers, actualPlayers);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByDocumentNumber() throws Exception {
		// @formatter:off
		Page<PlayerModel> expectedPlayers = new PageImpl<PlayerModel>(Arrays.asList(new PlayerModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QPlayerModel.playerModel.documentNumber.eq(1);	
			
		when(playerRepositoryMock.findAll(expression, pageable)).thenReturn(expectedPlayers);

		Page<PlayerModel> actualPlayers = playerService.getAll(null, null, null, null, null, 1, null, pageable);
		
		assertEquals(expectedPlayers.getTotalElements(), actualPlayers.getTotalElements());
		assertEquals(expectedPlayers, actualPlayers);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByStatus() throws Exception {
		// @formatter:off
		Page<PlayerModel> expectedPlayers = new PageImpl<PlayerModel>(Arrays.asList(new PlayerModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QPlayerModel.playerModel.status.eq(PlayerStatus.ENABLED);	
			
		when(playerRepositoryMock.findAll(expression, pageable)).thenReturn(expectedPlayers);

		Page<PlayerModel> actualPlayers = playerService.getAll(null, null, null, null, null, null, PlayerStatus.ENABLED, pageable);
		
		assertEquals(expectedPlayers.getTotalElements(), actualPlayers.getTotalElements());
		assertEquals(expectedPlayers, actualPlayers);
		// @formatter:on
	}
	
}
