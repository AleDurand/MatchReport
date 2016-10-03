package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.RoundModel;
import project.services.TournamentService;
import project.validators.RoundValidator;

@RestController
@RequestMapping(value = "/tournaments/{id}")
public class TournamentRoundController {

	@Autowired
	private TournamentService tournamentService;

	@Autowired
	private RoundValidator roundValidator;

	@RequestMapping(value = "/rounds", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<RoundModel>> getAllRounds(@PathVariable("id") Integer tournamentId) {
		List<RoundModel> rounds = tournamentService.getAllRounds(tournamentId);
		return new ResponseEntity<>(rounds, HttpStatus.OK);
	}

	@RequestMapping(value = "/rounds", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<RoundModel> addRound(@PathVariable("id") Integer tournamentId, @Validated RoundModel round) {
		RoundModel toReturn = tournamentService.addRound(tournamentId, round);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(roundValidator);
	}

}
