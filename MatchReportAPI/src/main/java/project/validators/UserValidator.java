package project.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
		UserModel user = (UserModel) target;
		if (user.getPassword() != null) {
			if (user.getPassword().length() < MINIMUM_PASSWORD_LENGTH) {
				errors.rejectValue("password", "field.min.length", new Object[] { MINIMUM_PASSWORD_LENGTH }, "");
			}
		}
	}

}