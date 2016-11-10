package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.EventModel;

@Component
public class EventValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EventModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EventModel event = (EventModel) target;

		if (event.getDiscriminator() == null) {
			errors.rejectValue("discriminator", "event.discriminator.not_null", "{event.discriminator.not_null}");
		}
		
		if (event.getType() == null) {
			errors.rejectValue("type", "event.type.not_null", "{event.type.not_null}");
		}
		
		if (event.getMinute() == null) {
			errors.rejectValue("minute", "event.minute.not_null", "{event.minute.not_null}");
		} else {
			if (event.getMinute() < 0) {
				errors.rejectValue("minute", "event.minute.positive", "{event.minute.positive}");
			}
		}
		
		if (event.getExtraMinute() != null && event.getExtraMinute() < 0) {
			errors.rejectValue("extraMinute", "event.extra_minute.positive", "{event.extra_minute.positive}");
		}		

	}

}
