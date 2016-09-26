package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.MatchModel;

@Component
public class MatchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MatchModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

}
