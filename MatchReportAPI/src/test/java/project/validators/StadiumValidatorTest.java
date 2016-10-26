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
import project.models.StadiumModel;
import project.utils.TestUtil;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class StadiumValidatorTest {

	private StadiumValidator validator;

	@Before
	public void setUp() {
		validator = new StadiumValidator();
	}

	@Test
	public void supports() {
		assertTrue(validator.supports(StadiumModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		StadiumModel stadium = new StadiumModel();
		stadium.setName("name");
		stadium.setAddress("address");
		BindException errors = new BindException(stadium, "stadium");
		ValidationUtils.invokeValidator(validator, stadium, errors);
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void invalidNameNull() {
		StadiumModel stadium = new StadiumModel();
		stadium.setName(null);
		stadium.setAddress("address");
		BindException errors = new BindException(stadium, "stadium");
		ValidationUtils.invokeValidator(validator, stadium, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidNameEmpty() {
		StadiumModel stadium = new StadiumModel();
		stadium.setName(" ");
		stadium.setAddress("address");
		BindException errors = new BindException(stadium, "stadium");
		ValidationUtils.invokeValidator(validator, stadium, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidNameTooLong() {
		StadiumModel stadium = new StadiumModel();
		stadium.setName(TestUtil.createStringWithLength(StadiumValidator.MAXIMUM_NAME_LENGTH + 1));
		stadium.setAddress("address");
		BindException errors = new BindException(stadium, "stadium");
		ValidationUtils.invokeValidator(validator, stadium, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidAddressNull() {
		StadiumModel stadium = new StadiumModel();
		stadium.setName("name");
		stadium.setAddress(null);
		BindException errors = new BindException(stadium, "stadium");
		ValidationUtils.invokeValidator(validator, stadium, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidAddressEmpty() {
		StadiumModel stadium = new StadiumModel();
		stadium.setName("name");
		stadium.setAddress(" ");
		BindException errors = new BindException(stadium, "stadium");
		ValidationUtils.invokeValidator(validator, stadium, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidAddressTooLong() {
		StadiumModel stadium = new StadiumModel();
		stadium.setName("name");
		stadium.setAddress(TestUtil.createStringWithLength(StadiumValidator.MAXIMUM_ADDRESS_LENGTH + 1));
		BindException errors = new BindException(stadium, "stadium");
		ValidationUtils.invokeValidator(validator, stadium, errors);
		assertTrue(errors.hasErrors());
	}

}
