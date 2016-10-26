package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.UserModel;

@Component
public class UserValidator implements Validator {

	public static final int MINIMUM_PASSWORD_LENGTH = 6;
	public static final int MAXIMUM_PASSWORD_LENGTH = 50;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserModel user = (UserModel) target;

		if (user.getUsername() == null) {
			errors.rejectValue("username", "user.username.not_null", "{user.username.not_null}");
		} else {
			if (user.getUsername().trim().isEmpty()) {
				errors.rejectValue("username", "user.username.not_empty", "{user.username.not_empty}");
			}
		}

		if (user.getPassword() == null) {
			errors.rejectValue("password", "user.password.not_null", "{user.password.not_null}");
		} else {
			if (user.getPassword().trim().isEmpty()) {
				errors.rejectValue("password", "user.password.not_empty", "{user.password.not_empty}");
			}

			int length = user.getPassword().length();
			if (length < MINIMUM_PASSWORD_LENGTH || length > MAXIMUM_PASSWORD_LENGTH) {
				Object[] args = new Object[] { MINIMUM_PASSWORD_LENGTH, MAXIMUM_PASSWORD_LENGTH };
				errors.rejectValue("password", "user.password.size", args, "{user.password.size}");
			}
		}

	}

}