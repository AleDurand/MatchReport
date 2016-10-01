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
import project.models.ClubModel;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class ClubValidatorTest {

	private ClubValidator validator;

	@Before
	public void setUp() {
		validator = new ClubValidator();
	}

	@Test
	public void supports() {
		assertTrue(validator.supports(ClubModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		ClubModel club = new ClubModel();
		BindException errors = new BindException(club, "club");
		ValidationUtils.invokeValidator(validator, club, errors);
		assertFalse(errors.hasErrors());
	}

}
