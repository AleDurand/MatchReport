package project.validators;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.PlayerModel;

@Component
public class PlayerValidator implements Validator {

	public static final int MAXIMUM_FIRSTNAME_LENGTH = 45;
	public static final int MAXIMUM_LASTNAME_LENGTH = 45;
	public static final int MAXIMUM_DOCUMENT_TYPE_LENGTH = 10;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PlayerModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PlayerModel player = (PlayerModel) target;

		if (player.getFirstname() == null) {
			errors.rejectValue("firstname", "player.firstname.not_null", "{player.firstname.not_null}");
		} else {
			if (player.getFirstname().trim().isEmpty()) {
				errors.rejectValue("firstname", "player.firstname.not_empty", "{player.firstname.not_empty}");
			}
			if (player.getFirstname().length() > MAXIMUM_FIRSTNAME_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_FIRSTNAME_LENGTH };
				errors.rejectValue("firstname", "player.firstname.size", args, "{player.firstname.size}");
			}
		}
		
		if (player.getLastname() == null) {
			errors.rejectValue("lastname", "player.lastname.not_null", "{player.lastname.not_null}");
		} else {
			if (player.getLastname().trim().isEmpty()) {
				errors.rejectValue("lastname", "player.lastname.not_empty", "{player.lastname.not_empty}");
			}
			if (player.getLastname().length() > MAXIMUM_LASTNAME_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_LASTNAME_LENGTH };
				errors.rejectValue("lastname", "player.lastname.size", args, "{player.lastname.size}");
			}
		}
		
		if (player.getBirthDate() == null) {
			errors.rejectValue("birthDate", "player.birthdate.not_null", "{player.birthdate.not_null}");
		} else {
			if (!player.getBirthDate().before(new Date())) {
				errors.rejectValue("birthDate", "player.birthdate.before_today", "{player.birthdate.before_today}");
			}
		}
		
		if (player.getDocumentNumber() == null) {
			errors.rejectValue("documentNumber", "player.document_number.not_null", "{player.document_number.not_null}");
		} else {
			if (player.getDocumentNumber() < 0) {
				errors.rejectValue("documentNumber", "player.document_number.greater_than_zero", "{player.document_number.greater_than_zero}");
			}
		}
		
		if (player.getDocumentType() == null) {
			errors.rejectValue("documentType", "player.document_type.not_null", "{player.document_type.not_null}");
		} else {
			if (player.getDocumentType().trim().isEmpty()) {
				errors.rejectValue("documentType", "player.document_type.not_empty", "{player.document_type.not_empty}");
			}
			if (player.getDocumentType().length() > MAXIMUM_DOCUMENT_TYPE_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_DOCUMENT_TYPE_LENGTH };
				errors.rejectValue("documentType", "player.document_type.size", args, "{player.document_type.size}");
			}
		}
	}

}
