package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.RoleModel;

@Component
public class RoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RoleModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

}
