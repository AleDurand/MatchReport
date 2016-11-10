package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.TeamEventModel;

@Component
public class TeamEventValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TeamEventModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TeamEventModel teamEvent = (TeamEventModel) target;
		
		if (teamEvent.getTeam() == null) {
			errors.rejectValue("team", "team_event.team.not_null", "{team_event.team.not_null}");
		} else {
			if (teamEvent.getTeam().getId() == null) {
				errors.rejectValue("team.id", "team_event.team.id_not_null", "{team_event.team.id_not_null}");
			}
		}
	}

}
