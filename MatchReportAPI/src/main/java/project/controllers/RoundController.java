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

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<RoundModel> create(@Validated @RequestBody RoundModel round) {
		RoundModel toReturn = RoundService.create(round);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
	}

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
	public ResponseEntity<List<RoundModel>> getAll() {
		List<RoundModel> rounds = RoundService.getAll();
		return new ResponseEntity<>(rounds, HttpStatus.OK);
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(roundValidator);
	}
}
