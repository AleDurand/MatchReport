package project.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import project.Application;
import project.models.ClubModel;
import project.models.EventModel;
import project.models.EventType;
import project.models.MatchModel;
import project.models.PlayerEventModel;
import project.models.TeamEventModel;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class CompositeValidatorTest {

	@Autowired
	private CompositeValidator validator;

	@Autowired
	private EventValidator eventValidator;

	@Autowired
	private TeamEventValidator teamEventValidator;

	@Before
	public void setUp() {
		List<Validator> validators = new ArrayList<Validator>();
		validators.add(eventValidator);
		validators.add(teamEventValidator);
		validator.setValidators(validators);
	}

	@Test
	public void supports() {
		assertTrue(validator.supports(EventModel.class));
		assertTrue(validator.supports(TeamEventModel.class));
		assertTrue(validator.supports(PlayerEventModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		TeamEventModel teamEvent = new TeamEventModel();
		teamEvent.setId(1);
		teamEvent.setDiscriminator("TEAM_EVENT");
		teamEvent.setMinute(1);
		teamEvent.setExtraMinute(null);
		teamEvent.setType(EventType.START_PERIOD);
		teamEvent.setMatch(new MatchModel());
		ClubModel club = new ClubModel();
		club.setId(1);
		teamEvent.setTeam(club);
		BindException errors = new BindException(teamEvent, "teamEvent");
		ValidationUtils.invokeValidator(validator, teamEvent, errors);
		assertFalse(errors.hasErrors());
	}

}
