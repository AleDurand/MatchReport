package project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.EventModel;
import project.services.MatchService;
import project.validators.CompositeValidator;
import project.validators.EventValidator;
import project.validators.PlayerEventValidator;
import project.validators.TeamEventValidator;

@RestController
@RequestMapping(value = "/matches/{id}")
public class MatchEventController {

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private EventValidator eventValidator;
	
	@Autowired
	private PlayerEventValidator playerEventValidator;
	
	@Autowired
	private TeamEventValidator teamEventValidator;
	
	@Autowired
	private CompositeValidator compositeValidator;

	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<EventModel>> getAllEvents(@PathVariable("id") Integer matchId) {
		List<EventModel> events = matchService.getAllEvents(matchId);
		return new ResponseEntity<>(events, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/events", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<EventModel> addEvent(@PathVariable("id") Integer matchId, @Validated @RequestBody EventModel event) {
		EventModel toReturn = matchService.addEvent(matchId, event);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder, HttpServletRequest request) {
		List<Validator> validators = new ArrayList<Validator>();
		validators.add(eventValidator);
		validators.add(teamEventValidator);
		validators.add(playerEventValidator);
		compositeValidator.setValidators(validators);
		binder.setValidator(compositeValidator);
	}
}
