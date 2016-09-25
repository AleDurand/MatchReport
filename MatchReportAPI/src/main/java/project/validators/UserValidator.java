package project.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.UserModel;

public class UserValidator implements Validator {

	private static final Integer MINIMUM_PASSWORD_LENGTH = 6;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

	}

}