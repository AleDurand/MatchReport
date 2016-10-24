package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.MatchModel;
import project.services.RoundService;
import project.validators.MatchValidator;

@RestController
@RequestMapping(value = "/rounds/{id}")
public class RoundMatchController {

	@Autowired
	private RoundService roundService;

	@Autowired
	private MatchValidator matchValidator;

	@RequestMapping(value = "/matches", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<MatchModel>> getAllRounds(@PathVariable("id") Integer roundId) {
		List<MatchModel> matches = roundService.getAllMatches(roundId);
		return new ResponseEntity<>(matches, HttpStatus.OK);
	}

	@RequestMapping(value = "/rounds", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<MatchModel> addRound(@PathVariable("id") Integer roundId, @Validated @RequestBody MatchModel match) {
		MatchModel toReturn = roundService.addMatch(roundId, match);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);	
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(matchValidator);
	}

}
