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
import project.models.EventModel;
import project.models.EventType;
import project.models.MatchModel;
import project.models.PlayerEventModel;
import project.models.TeamEventModel;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class EventValidatorTest {

	@Autowired
	private EventValidator validator;
	
	@Test
	public void supports() {
		assertTrue(validator.supports(EventModel.class));
		assertTrue(validator.supports(TeamEventModel.class));
		assertTrue(validator.supports(PlayerEventModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		EventModel event = new EventModel();
		event.setId(1);
		event.setDiscriminator("EVENT");
		event.setMinute(1);
		event.setExtraMinute(null);
		event.setType(EventType.START_PERIOD);
		event.setMatch(new MatchModel());
		BindException errors = new BindException(event, "event");
		ValidationUtils.invokeValidator(validator, event, errors);
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void invalidDiscriminatorNull() {
		EventModel event = new EventModel();
		event.setId(1);
		event.setDiscriminator(null);
		event.setMinute(1);
		event.setExtraMinute(null);
		event.setType(EventType.START_PERIOD);
		event.setMatch(new MatchModel());
		BindException errors = new BindException(event, "event");
		ValidationUtils.invokeValidator(validator, event, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidTypeNull() {
		EventModel event = new EventModel();
		event.setId(1);
		event.setDiscriminator("EVENT");
		event.setMinute(1);
		event.setExtraMinute(null);
		event.setType(null);
		event.setMatch(new MatchModel());
		BindException errors = new BindException(event, "event");
		ValidationUtils.invokeValidator(validator, event, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidMinuteNull() {
		EventModel event = new EventModel();
		event.setId(1);
		event.setDiscriminator("EVENT");
		event.setMinute(null);
		event.setExtraMinute(null);
		event.setType(EventType.START_PERIOD);
		event.setMatch(new MatchModel());
		BindException errors = new BindException(event, "event");
		ValidationUtils.invokeValidator(validator, event, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidMinuteNonPositive() {
		EventModel event = new EventModel();
		event.setId(1);
		event.setDiscriminator("EVENT");
		event.setMinute(-1);
		event.setExtraMinute(null);
		event.setType(EventType.START_PERIOD);
		event.setMatch(new MatchModel());
		BindException errors = new BindException(event, "event");
		ValidationUtils.invokeValidator(validator, event, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidExtraMinuteNonPositive() {
		EventModel event = new EventModel();
		event.setId(1);
		event.setDiscriminator("EVENT");
		event.setMinute(1);
		event.setExtraMinute(-1);
		event.setType(EventType.START_PERIOD);
		event.setMatch(new MatchModel());
		BindException errors = new BindException(event, "event");
		ValidationUtils.invokeValidator(validator, event, errors);
		assertTrue(errors.hasErrors());
	}

}
