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

import project.models.TournamentModel;
import project.services.TournamentService;
import project.validators.TournamentValidator;

@RestController
@RequestMapping(value = "/tournaments")
public class TournamentController {

	@Autowired
	private TournamentService tournamentService;

	@Autowired
	private TournamentValidator tournamentValidator;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<TournamentModel> create(@Validated @RequestBody TournamentModel tournament) {
		TournamentModel toReturn = tournamentService.create(tournament);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<TournamentModel> read(@PathVariable("id") Integer id) {
		TournamentModel toReturn = tournamentService.read(id);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		tournamentService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<TournamentModel>> getAll() {
		List<TournamentModel> tournaments = tournamentService.getAll();
		return new ResponseEntity<>(tournaments, HttpStatus.OK);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(tournamentValidator);
	}

}