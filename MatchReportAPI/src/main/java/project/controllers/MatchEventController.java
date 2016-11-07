package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.EventModel;
import project.services.MatchService;
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

	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<EventModel>> getAllEvents(@PathVariable("id") Integer matchId) {
		List<EventModel> events = matchService.getAllEvents(matchId);
		return new ResponseEntity<>(events, HttpStatus.OK);
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(eventValidator, playerEventValidator, teamEventValidator);
	}
}
