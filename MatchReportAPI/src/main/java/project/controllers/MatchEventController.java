package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.EventModel;
import project.services.EventService;

@RestController
@RequestMapping(value = "/matches/{id}")
public class MatchEventController {

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Page<EventModel>> getAllRounds(Pageable pageable) {
		Page<EventModel> events = eventService.getAll(pageable);
		return new ResponseEntity<>(events, HttpStatus.OK);
	}
}
