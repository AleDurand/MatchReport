package project.validators;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.TournamentModel;

@Component
public class TournamentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TournamentModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TournamentModel tournament = (TournamentModel) target;

		if (tournament.getInitDate() == null) {
			errors.rejectValue("initDate", "tournament.init_date.not_null", "{tournament.init_date.not_null}");
		} else {
			if (tournament.getInitDate().before(new Date())) {
				errors.rejectValue("initDate", "tournament.init_date.before_today", "{tournament.init_date.before_today}");
			}
		}

		if (tournament.getEndDate() == null) {
			errors.rejectValue("endDate", "tournament.end_date.not_null", "{tournament.end_date.not_null}");
		} else {
			if (tournament.getEndDate().before(new Date())) {
				errors.rejectValue("endDate", "tournament.end_date.after_today", "{tournament.end_date.after_today}");
			}
		}

		if (tournament.getInitDate() != null && tournament.getEndDate() != null) {
			if (tournament.getInitDate().equals(tournament.getEndDate())
					|| tournament.getInitDate().after(tournament.getEndDate())) {
				errors.reject("tournament.dates.invalid", "{tournament.dates.invalid}");
			}

		}

	}
}
