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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.models.MatchModel;
import project.services.MatchService;
import project.validators.MatchValidator;

@RestController
@RequestMapping(value = "/matches")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@Autowired
	private MatchValidator matchValidator;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<MatchModel> read(@PathVariable("id") Integer id) {
		MatchModel toReturn = matchService.read(id);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		matchService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<MatchModel>> getAll( // @formatter:off 
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "status", required = false) Integer status,
			@RequestParam(name = "stadium", required = false) Integer idStadium,
			@RequestParam(name = "local", required = false) Integer idLocal,
			@RequestParam(name = "visitor", required = false) Integer idVisitor,
			@RequestParam(name = "round", required = false) Integer idRound
	) { // @formatter:on
		List<MatchModel> matches = matchService.getAll(id, status, idStadium, idLocal, idVisitor, idRound);
		return new ResponseEntity<>(matches, HttpStatus.OK);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(matchValidator);
	}

}
