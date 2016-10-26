package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.RoleModel;

@Component
public class RoleValidator implements Validator {

	public static final int MAXIMUM_NAME_LENGTH = 50;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RoleModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RoleModel role = (RoleModel) target;

		if (role.getName() == null) {
			errors.rejectValue("name", "role.name.not_null", "{role.name.not_null}");
		} else {
			if (role.getName().trim().isEmpty()) {
				errors.rejectValue("name", "role.name.not_empty", "{role.name.not_empty}");
			}
			if (role.getName().length() > MAXIMUM_NAME_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_NAME_LENGTH };
				errors.rejectValue("name", "role.name.size", args, "{role.name.size}");
			}
		}
	}

}
