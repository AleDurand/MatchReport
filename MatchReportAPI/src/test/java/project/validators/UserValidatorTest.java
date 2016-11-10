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
import project.models.UserModel;
import project.utils.TestUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class UserValidatorTest {

	@Autowired
	private UserValidator validator;

	@Test
	public void supports() {
		assertTrue(validator.supports(UserModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		UserModel user = new UserModel();
		user.setUsername("username");
		user.setPassword("password");
		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void invalidUsernameNull() {
		UserModel user = new UserModel();
		user.setUsername(null);
		user.setPassword("password");
		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidUsernameEmpty() {
		UserModel user = new UserModel();
		user.setUsername("       ");
		user.setPassword("password");
		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidPasswordNull() {
		UserModel user = new UserModel();
		user.setUsername("username");
		user.setPassword(null);
		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidPasswordEmpty() {
		UserModel user = new UserModel();
		user.setUsername("username");
		user.setPassword("       ");
		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidPasswordTooShort() {
		UserModel user = new UserModel();
		user.setUsername("username");
		user.setPassword(TestUtil.createStringWithLength(UserValidator.MINIMUM_PASSWORD_LENGTH - 1));
		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidPasswordTooLong() {
		UserModel user = new UserModel();
		user.setUsername("username");
		user.setPassword(TestUtil.createStringWithLength(UserValidator.MAXIMUM_PASSWORD_LENGTH + 1));
		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

}
