package project.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import project.models.RoundModel;
import project.utils.TestUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class RoundValidatorTest {

	private RoundValidator validator;

	@Before
	public void setUp() {
		validator = new RoundValidator();
	}

	@Test
	public void supports() {
		assertTrue(validator.supports(RoundModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		RoundModel round = new RoundModel();
		round.setNumber(1);
		round.setDescription("description");
		BindException errors = new BindException(round, "round");
		ValidationUtils.invokeValidator(validator, round, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void invalidNumberNull() {
		RoundModel round = new RoundModel();
		round.setNumber(null);
		round.setDescription("description");
		BindException errors = new BindException(round, "round");
		ValidationUtils.invokeValidator(validator, round, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidNumberLessThanZero() {
		RoundModel round = new RoundModel();
		round.setNumber(-1);
		round.setDescription("description");
		BindException errors = new BindException(round, "round");
		ValidationUtils.invokeValidator(validator, round, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidDescriptionNull() {
		RoundModel round = new RoundModel();
		round.setNumber(1);
		round.setDescription(null);
		BindException errors = new BindException(round, "round");
		ValidationUtils.invokeValidator(validator, round, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidDescriptionEmpty() {
		RoundModel round = new RoundModel();
		round.setNumber(1);
		round.setDescription(" ");
		BindException errors = new BindException(round, "round");
		ValidationUtils.invokeValidator(validator, round, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidDescriptionTooLong() {
		RoundModel round = new RoundModel();
		round.setNumber(1);
		round.setDescription(TestUtil.createStringWithLength(RoundValidator.MAXIMUM_DESCRIPTION_LENGTH + 1));
		BindException errors = new BindException(round, "round");
		ValidationUtils.invokeValidator(validator, round, errors);
		assertTrue(errors.hasErrors());
	}

}
