package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.ClubModel;

@Component
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
