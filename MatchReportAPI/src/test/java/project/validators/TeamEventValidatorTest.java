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
import project.models.TeamEventModel;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class TeamEventValidatorTest {

	@Autowired
	private TeamEventValidator validator;

	@Test
	public void supports() {
		assertTrue(validator.supports(TeamEventModel.class));
		assertTrue(validator.supports(PlayerEventModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		TeamEventModel teamEvent = new TeamEventModel();
		teamEvent.setId(1);
		teamEvent.setMinute(1);
		teamEvent.setExtraMinute(null);
		teamEvent.setType(EventType.START_PERIOD);
		teamEvent.setMatch(new MatchModel());
		teamEvent.setTeam(new ClubModel());
		BindException errors = new BindException(teamEvent, "playerEvent");
		ValidationUtils.invokeValidator(validator, teamEvent, errors);
		assertFalse(errors.hasErrors());
	}
	
}
