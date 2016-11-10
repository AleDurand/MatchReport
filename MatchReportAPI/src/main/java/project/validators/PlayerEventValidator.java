package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.PlayerEventModel;

@Component
public class PlayerEventValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PlayerEventModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PlayerEventModel playerEvent = (PlayerEventModel) target;
		
		if (playerEvent.getPlayer() == null) {
			errors.rejectValue("player", "player_event.player.not_null", "{player_event.player.not_null}");
		} else {
			if (playerEvent.getPlayer().getId() == null) {
				errors.rejectValue("player.id", "player_event.player.id_not_null", "{player_event.player.id_not_null}");
			}
		}
	}

}
