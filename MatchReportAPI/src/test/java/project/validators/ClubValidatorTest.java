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
import project.utils.TestUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class ClubValidatorTest {

	@Autowired
	private ClubValidator validator;

	@Test
	public void supports() {
		assertTrue(validator.supports(ClubModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		ClubModel club = new ClubModel();
		club.setName("name");
		club.setAddress("address");
		BindException errors = new BindException(club, "club");
		ValidationUtils.invokeValidator(validator, club, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void invalidNameNull() {
		ClubModel club = new ClubModel();
		club.setName(null);
		club.setAddress("address");
		BindException errors = new BindException(club, "club");
		ValidationUtils.invokeValidator(validator, club, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidNameEmpty() {
		ClubModel club = new ClubModel();
		club.setName(" ");
		club.setAddress("address");
		BindException errors = new BindException(club, "club");
		ValidationUtils.invokeValidator(validator, club, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidNameTooLong() {
		ClubModel club = new ClubModel();
		club.setName(TestUtil
				.createStringWithLength(ClubValidator.MAXIMUM_NAME_LENGTH + 1));
		club.setAddress("address");
		BindException errors = new BindException(club, "club");
		ValidationUtils.invokeValidator(validator, club, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidAddressNull() {
		ClubModel club = new ClubModel();
		club.setName("name");
		club.setAddress(null);
		BindException errors = new BindException(club, "club");
		ValidationUtils.invokeValidator(validator, club, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidAddressEmpty() {
		ClubModel club = new ClubModel();
		club.setName("name");
		club.setAddress(" ");
		BindException errors = new BindException(club, "club");
		ValidationUtils.invokeValidator(validator, club, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidAddressTooLong() {
		ClubModel club = new ClubModel();
		club.setName("name");
		club.setAddress(TestUtil.createStringWithLength(ClubValidator.MAXIMUM_ADDRESS_LENGTH + 1));
		BindException errors = new BindException(club, "club");
		ValidationUtils.invokeValidator(validator, club, errors);
		assertTrue(errors.hasErrors());
	}

}
