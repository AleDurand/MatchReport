package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.RoundModel;

@Component
public class RoundValidator implements Validator {

	public static final int MAXIMUM_DESCRIPTION_LENGTH = 45;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RoundModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RoundModel round = (RoundModel) target;

		if (round.getNumber() == null) {
			errors.rejectValue("number", "round.number.not_null", "{round.number.not_null}");
		} else {
			if (round.getNumber() < 0) {
				errors.rejectValue("number", "round.number.greater_than_zero", "{round.number.greater_than_zero}");
			}
		}
		
		if (round.getDescription() == null) {
			errors.rejectValue("description", "round.description.not_null", "{round.description.not_null}");
		} else {
			if (round.getDescription().trim().isEmpty()) {
				errors.rejectValue("description", "round.description.not_empty", "{round.description.not_empty}");
			}
			if (round.getDescription().length() > MAXIMUM_DESCRIPTION_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_DESCRIPTION_LENGTH };
				errors.rejectValue("description", "round.description.size", args, "{round.description.size}");
			}
		}

	}

}
