package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.PlayerEventModel;

@Component
public class PlayerEventValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PlayerEventModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

}
