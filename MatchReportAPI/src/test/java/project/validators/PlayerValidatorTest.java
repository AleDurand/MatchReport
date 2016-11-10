package project.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
import project.models.PlayerModel;
import project.utils.TestUtil;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class PlayerValidatorTest {

	@Autowired
	private PlayerValidator validator;

	@Test
	public void supports() {
		assertTrue(validator.supports(PlayerModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertFalse(errors.hasErrors());
	}
	
	@Test
	public void invalidFirstnameNull() {
		PlayerModel player = new PlayerModel();
		player.setFirstname(null);
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidFirstnameEmpty() {
		PlayerModel player = new PlayerModel();
		player.setFirstname(" ");
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidFirstnameTooLong() {
		PlayerModel player = new PlayerModel();
		player.setFirstname(TestUtil.createStringWithLength(PlayerValidator.MAXIMUM_FIRSTNAME_LENGTH + 1));
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidLastnameNull() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname(null);
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidLastnameEmpty() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname(" ");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidLastnameTooLong() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname(TestUtil.createStringWithLength(PlayerValidator.MAXIMUM_LASTNAME_LENGTH + 1));
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidbirthDateNull() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname("lastname");
		player.setBirthDate(null);
		player.setDocumentType("dni");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidbirthDateAfterToday() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidDocumentTypeNull() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType(null);
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidDocumentTypeEmpty() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType(" ");
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidDocumentTypeTooLong() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType(TestUtil.createStringWithLength(PlayerValidator.MAXIMUM_DOCUMENT_TYPE_LENGTH + 1));
		player.setDocumentNumber(10000000);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidDocumentNumberNull() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(null);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidDocumentNumberLessThanZero() {
		PlayerModel player = new PlayerModel();
		player.setFirstname("firstname");
		player.setLastname("lastname");
		player.setBirthDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1)));
		player.setDocumentType("dni");
		player.setDocumentNumber(-1);
		BindException errors = new BindException(player, "player");
		ValidationUtils.invokeValidator(validator, player, errors);
		assertTrue(errors.hasErrors());
	}

}
