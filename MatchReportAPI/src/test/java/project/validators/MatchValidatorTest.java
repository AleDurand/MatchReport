package project.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import project.Application;
import project.models.MatchModel;

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
		MatchModel match = new MatchModel();
		BindException errors = new BindException(match, "match");
		ValidationUtils.invokeValidator(validator, match, errors);
		assertFalse(errors.hasErrors());
	}

}
