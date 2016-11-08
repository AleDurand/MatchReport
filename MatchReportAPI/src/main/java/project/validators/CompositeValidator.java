package project.validators;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CompositeValidator implements Validator {

	private List<Validator> validators;

	@Override
	public boolean supports(final Class<?> clazz) {
		for (Validator v : validators) {
			if (v.supports(clazz)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void validate(final Object obj, final Errors errors) {
		for (Validator v : validators) {
			if (v.supports(obj.getClass())) {
				v.validate(obj, errors);
			}
		}
	}

	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}
}