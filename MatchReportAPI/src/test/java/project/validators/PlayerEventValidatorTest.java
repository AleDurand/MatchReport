package project.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import project.Application;
import project.models.ClubModel;
import project.models.EventType;
import project.models.MatchModel;
import project.models.PlayerEventModel;
import project.models.PlayerModel;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class PlayerEventValidatorTest {

	@Autowired
	private PlayerEventValidator validator;

	@Test
	public void supports() {
		assertTrue(validator.supports(PlayerEventModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		PlayerEventModel playerEvent = new PlayerEventModel();
		playerEvent.setId(1);
		playerEvent.setDiscriminator("PLAYER_EVENT");
		playerEvent.setMinute(1);
		playerEvent.setExtraMinute(null);
		playerEvent.setType(EventType.START_PERIOD);
		playerEvent.setMatch(new MatchModel());
		PlayerModel player = new PlayerModel();
		player.setId(1);
		playerEvent.setPlayer(player);
		playerEvent.setTeam(new ClubModel());
		BindException errors = new BindException(playerEvent, "playerEvent");
		ValidationUtils.invokeValidator(validator, playerEvent, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void invalidPlayerNull() {
		PlayerEventModel playerEvent = new PlayerEventModel();
		playerEvent.setId(1);
		playerEvent.setDiscriminator("PLAYER_EVENT");
		playerEvent.setMinute(1);
		playerEvent.setExtraMinute(null);
		playerEvent.setType(EventType.START_PERIOD);
		playerEvent.setMatch(new MatchModel());
		ClubModel club = new ClubModel();
		club.setId(1);
		playerEvent.setTeam(club);
		playerEvent.setPlayer(null);
		BindException errors = new BindException(playerEvent, "playerEvent");
		ValidationUtils.invokeValidator(validator, playerEvent, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidPlayerIdNull() {
		PlayerEventModel playerEvent = new PlayerEventModel();
		playerEvent.setId(1);
		playerEvent.setDiscriminator("PLAYER_EVENT");
		playerEvent.setMinute(1);
		playerEvent.setExtraMinute(null);
		playerEvent.setType(EventType.START_PERIOD);
		playerEvent.setMatch(new MatchModel());
		ClubModel club = new ClubModel();
		club.setId(1);
		playerEvent.setTeam(club);
		playerEvent.setPlayer(new PlayerModel());
		BindException errors = new BindException(playerEvent, "playerEvent");
		ValidationUtils.invokeValidator(validator, playerEvent, errors);
		assertTrue(errors.hasErrors());
	}

}
