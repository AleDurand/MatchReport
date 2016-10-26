package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.ClubModel;

@Component
public class ClubValidator implements Validator {

	public static final int MAXIMUM_NAME_LENGTH = 45;
	public static final int MAXIMUM_ADDRESS_LENGTH = 45;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ClubModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ClubModel club = (ClubModel) target;

		if (club.getName() == null) {
			errors.rejectValue("name", "club.name.not_null", "{club.name.not_null}");
		} else {
			if (club.getName().trim().isEmpty()) {
				errors.rejectValue("name", "club.name.not_empty", "{club.name.not_empty}");
			}
			if (club.getName().length() > MAXIMUM_NAME_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_NAME_LENGTH };
				errors.rejectValue("name", "club.name.size", args, "{club.name.size}");
			}
		}
		
		if (club.getAddress() == null) {
			errors.rejectValue("address", "club.address.not_null", "{club.address.not_null}");
		} else {
			if (club.getAddress().trim().isEmpty()) {
				errors.rejectValue("address", "club.address.not_empty", "{club.address.not_empty}");
			}
			if (club.getAddress().length() > MAXIMUM_ADDRESS_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_ADDRESS_LENGTH };
				errors.rejectValue("address", "club.address.size", args, "{club.address.size}");
			}
		}
		
	}

}
