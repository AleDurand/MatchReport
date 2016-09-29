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
		BindException errors = new BindException(round, "round");
		ValidationUtils.invokeValidator(validator, round, errors);
		assertFalse(errors.hasErrors());
	}

}
