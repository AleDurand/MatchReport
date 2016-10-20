package project.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import project.Application;
import project.models.TournamentModel;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class TournamentValidatorTest {

	private TournamentValidator validator;

	@Before
	public void setUp() {
		validator = new TournamentValidator();
	}

	@Test
	public void supports() {
		assertTrue(validator.supports(TournamentModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		TournamentModel tournament = new TournamentModel();
		tournament.setInitDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		tournament.setEndDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(2)));
		BindException errors = new BindException(tournament, "tournament");
		ValidationUtils.invokeValidator(validator, tournament, errors);
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void invalidInitDateNull() {
		TournamentModel tournament = new TournamentModel();
		tournament.setInitDate(null);
		tournament.setEndDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(2)));
		BindException errors = new BindException(tournament, "tournament");
		ValidationUtils.invokeValidator(validator, tournament, errors);
		assertTrue(errors.hasErrors());
	}
	
	
	@Test
	public void invalidInitDate() {
		TournamentModel tournament = new TournamentModel();
		tournament.setInitDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		tournament.setEndDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(2)));
		BindException errors = new BindException(tournament, "tournament");
		ValidationUtils.invokeValidator(validator, tournament, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidEndDate() {
		TournamentModel tournament = new TournamentModel();
		tournament.setInitDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		tournament.setEndDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		BindException errors = new BindException(tournament, "tournament");
		ValidationUtils.invokeValidator(validator, tournament, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidEndDateNull() {
		TournamentModel tournament = new TournamentModel();
		tournament.setInitDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		tournament.setEndDate(null);
		BindException errors = new BindException(tournament, "tournament");
		ValidationUtils.invokeValidator(validator, tournament, errors);
		assertTrue(errors.hasErrors());
	}

	
	@Test
	public void invalidDates() {
		TournamentModel tournament = new TournamentModel();
		tournament.setInitDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(3)));
		tournament.setEndDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(2)));
		BindException errors = new BindException(tournament, "tournament");
		ValidationUtils.invokeValidator(validator, tournament, errors);
		assertTrue(errors.hasErrors());
	}

}
