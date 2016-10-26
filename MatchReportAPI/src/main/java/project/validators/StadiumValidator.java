package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.StadiumModel;

@Component
public class StadiumValidator implements Validator {

	public static final int MAXIMUM_NAME_LENGTH = 45;
	public static final int MAXIMUM_ADDRESS_LENGTH = 45;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return StadiumModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StadiumModel stadium = (StadiumModel) target;
		
		if (stadium.getName() == null) {
			errors.rejectValue("name", "stadium.name.not_null", "{stadium.name.not_null}");
		} else {
			if (stadium.getName().trim().isEmpty()) {
				errors.rejectValue("name", "stadium.name.not_empty", "{stadium.name.not_empty}");
			}
			if (stadium.getName().length() > MAXIMUM_NAME_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_NAME_LENGTH };
				errors.rejectValue("name", "stadium.name.size", args, "{stadium.name.size}");
			}
		}
		
		if (stadium.getAddress() == null) {
			errors.rejectValue("address", "stadium.address.not_null", "{stadium.address.not_null}");
		} else {
			if (stadium.getAddress().trim().isEmpty()) {
				errors.rejectValue("address", "stadium.address.not_empty", "{stadium.address.not_empty}");
			}
			if (stadium.getAddress().length() > MAXIMUM_ADDRESS_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_ADDRESS_LENGTH };
				errors.rejectValue("address", "stadium.address.size", args, "{stadium.address.size}");
			}
		}	
	}

}
