package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.models.RoundModel;
import project.services.RoundService;
import project.validators.RoundValidator;

@RestController
@RequestMapping(value = "/rounds")
public class RoundController {

	@Autowired
	private RoundService RoundService;

	@Autowired
	private RoundValidator roundValidator;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RoundModel> read(@PathVariable("id") Integer id) {
		RoundModel toReturn = RoundService.read(id);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		RoundService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Page<RoundModel>> getAll( // @formatter:off
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "number", required = false) Integer number,
			@RequestParam(name = "description", required = false) String description,
			@RequestParam(name = "tournament", required = false) Integer tournament,
			Pageable pageable
	) { // @formatter:on
		Page<RoundModel> rounds = RoundService.getAll(id, number, description, tournament, pageable);
		return new ResponseEntity<>(rounds, HttpStatus.OK);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(roundValidator);
	}
}
