package project.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.ClubModel;

public class ClubValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ClubModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

	}

}
