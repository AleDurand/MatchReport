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
import project.models.PlayerModel;
import project.models.PlayerStatus;
import project.models.QPlayerModel;
import project.repositories.PlayerRepository;
import project.services.PlayerService;

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
		List<PlayerModel> expectedPlayers = Arrays.asList(new PlayerModel());
		QPlayerModel player = QPlayerModel.playerModel;
		when(playerRepositoryMock.findAll(player.instanceOfAny())).thenReturn(expectedPlayers);

		List<PlayerModel> actualPlayers = playerService.getAll(null, null, null, null, null, null, null);

		assertEquals(expectedPlayers.size(), actualPlayers.size());
		assertEquals(expectedPlayers, actualPlayers);
		// @formatter:on
	}
}
