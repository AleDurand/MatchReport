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
import project.models.ClubModel;
import project.models.MatchModel;
import project.models.StadiumModel;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class MatchValidatorTest {

	private MatchValidator validator;

	@Before
	public void setUp() {
		validator = new MatchValidator();
	}

	@Test
	public void supports() {
		assertTrue(validator.supports(MatchModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		StadiumModel stadium = new StadiumModel();
		stadium.setId(1);
		ClubModel local = new ClubModel();
		local.setId(1);
		ClubModel visitor = new ClubModel();
		visitor.setId(1);
		MatchModel match = new MatchModel();
		match.setDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		match.setStadium(stadium);
		match.setLocal(local);
		match.setVisitor(visitor);
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void invalidDateNull() {
		StadiumModel stadium = new StadiumModel();
		stadium.setId(1);
		ClubModel local = new ClubModel();
		local.setId(1);
		ClubModel visitor = new ClubModel();
		visitor.setId(1);
		MatchModel match = new MatchModel();
		match.setDate(null);
		match.setStadium(stadium);
		match.setLocal(local);
		match.setVisitor(visitor);
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidDateBeforeThanToday() {
		StadiumModel stadium = new StadiumModel();
		stadium.setId(1);
		ClubModel local = new ClubModel();
		local.setId(1);
		ClubModel visitor = new ClubModel();
		visitor.setId(1);
		MatchModel match = new MatchModel();
		match.setDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		match.setStadium(stadium);
		match.setLocal(local);
		match.setVisitor(visitor);
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidStadiumNull() {
		ClubModel local = new ClubModel();
		local.setId(1);
		ClubModel visitor = new ClubModel();
		visitor.setId(1);
		MatchModel match = new MatchModel();
		match.setDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		match.setStadium(null);
		match.setLocal(local);
		match.setVisitor(visitor);
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidStadiumIdNull() {
		StadiumModel stadium = new StadiumModel();
		ClubModel local = new ClubModel();
		local.setId(1);
		ClubModel visitor = new ClubModel();
		visitor.setId(1);
		MatchModel match = new MatchModel();
		match.setDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		match.setStadium(stadium);
		match.setLocal(local);
		match.setVisitor(visitor);
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidLocalNull() {
		StadiumModel stadium = new StadiumModel();
		stadium.setId(1);
		ClubModel visitor = new ClubModel();
		visitor.setId(1);
		MatchModel match = new MatchModel();
		match.setDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		match.setStadium(stadium);
		match.setLocal(null);
		match.setVisitor(visitor);
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidLocalIdNull() {
		StadiumModel stadium = new StadiumModel();
		stadium.setId(1);
		ClubModel local = new ClubModel();		
		ClubModel visitor = new ClubModel();
		visitor.setId(1);
		MatchModel match = new MatchModel();
		match.setDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		match.setStadium(stadium);
		match.setLocal(local);
		match.setVisitor(visitor);
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidVisitorNull() {
		StadiumModel stadium = new StadiumModel();
		stadium.setId(1);
		ClubModel local = new ClubModel();
		local.setId(1);
		MatchModel match = new MatchModel();
		match.setDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		match.setStadium(stadium);
		match.setLocal(local);
		match.setVisitor(null);
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidVisitorIdNull() {
		StadiumModel stadium = new StadiumModel();
		stadium.setId(1);
		ClubModel local = new ClubModel();
		local.setId(1);
		ClubModel visitor = new ClubModel();
		MatchModel match = new MatchModel();
		match.setDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		match.setStadium(stadium);
		match.setLocal(local);
		match.setVisitor(visitor);
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertTrue(errors.hasErrors());
	}

}
