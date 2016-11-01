package project.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.models.PlayerModel;
import project.services.PlayerService;
import project.validators.PlayerValidator;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerValidator playerValidator;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<PlayerModel> create(@Validated @RequestBody PlayerModel player) {
		PlayerModel toReturn = playerService.create(player);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<PlayerModel> read(@PathVariable("id") Integer id) {
		PlayerModel toReturn = playerService.read(id);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		playerService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<PlayerModel>> getAll( // @formatter:off
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "firstname", required = false) String firstname,
			@RequestParam(name = "lastname", required = false) String lastname,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
			@RequestParam(name = "birth-date-before", required = false) Date birthDateBefore,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
			@RequestParam(name = "birth-date-after", required = false) Date birthDateAfter,
			@RequestParam(name = "document-number", required = false) Integer documentNumber,
			@RequestParam(name = "status", required = false) Integer status
	) { // @formatter:on
		List<PlayerModel> players = playerService.getAll(id, firstname, lastname, birthDateBefore, birthDateAfter, documentNumber, status);
		return new ResponseEntity<>(players, HttpStatus.OK);
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(playerValidator);
	}
}
