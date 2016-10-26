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
import project.models.RoleModel;
import project.utils.TestUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class RoleValidatorTest {

	private RoleValidator validator;

	@Before
	public void setUp() {
		validator = new RoleValidator();
	}

	@Test
	public void supports() {
		assertTrue(validator.supports(RoleModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		RoleModel role = new RoleModel();
		role.setName("name");
		BindException errors = new BindException(role, "role");
		ValidationUtils.invokeValidator(validator, role, errors);
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void invalidNameNull() {
		RoleModel role = new RoleModel();
		role.setName(null);
		BindException errors = new BindException(role, "role");
		ValidationUtils.invokeValidator(validator, role, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidNameEmpty() {
		RoleModel role = new RoleModel();
		role.setName(" ");
		BindException errors = new BindException(role, "role");
		ValidationUtils.invokeValidator(validator, role, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidNameTooLong() {
		RoleModel role = new RoleModel();
		role.setName(TestUtil.createStringWithLength(RoleValidator.MAXIMUM_NAME_LENGTH + 1));
		BindException errors = new BindException(role, "role");
		ValidationUtils.invokeValidator(validator, role, errors);
		assertTrue(errors.hasErrors());
	}

}
