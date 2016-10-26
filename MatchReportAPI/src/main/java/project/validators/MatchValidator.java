package project.validators;

import java.util.Date;

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
		MatchModel match = (MatchModel) target;
		
		if (match.getDate() == null) {
			errors.rejectValue("date", "match.date.not_null", "{match.date.not_null}");
		} else {
			if (!match.getDate().after(new Date())) {
				errors.rejectValue("date", "match.date.after_today", "{match.date.after_today}");
			}
		}
		
		if (match.getStadium() == null) {
			errors.rejectValue("stadium", "match.stadium.not_null", "{match.stadium.not_null}");
		} else {
			if (match.getStadium().getId() == null) {
				errors.rejectValue("stadium.id", "match.stadium.id_not_null", "{match.stadium.id_not_null}");
			}
		}
		
		if (match.getLocal() == null) {
			errors.rejectValue("local", "match.local.not_null", "{match.stadium.not_null}");
		} else {
			if (match.getLocal().getId() == null) {
				errors.rejectValue("local.id", "match.local.id_not_null", "{match.stadium.id_not_null}");
			}
		}
		
		if (match.getVisitor() == null) {
			errors.rejectValue("visitor", "match.visitor.not_null", "{match.visitor.not_null}");
		} else {
			if (match.getVisitor().getId() == null) {
				errors.rejectValue("visitor.id", "match.visitor.id_not_null", "{match.visitor.id_not_null}");
			}
		}
	}

}
