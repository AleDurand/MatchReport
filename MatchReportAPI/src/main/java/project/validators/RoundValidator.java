package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.RoundModel;

@Component
public class RoundValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RoundModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

}
