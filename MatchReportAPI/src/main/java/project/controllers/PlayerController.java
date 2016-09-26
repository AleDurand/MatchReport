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

import project.models.PlayerModel;
import project.services.PlayerService;
import project.validators.StadiumValidator;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private StadiumValidator stadiumValidator;

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
	public ResponseEntity<List<PlayerModel>> getAll() {
		List<PlayerModel> players = playerService.getAll();
		return new ResponseEntity<>(players, HttpStatus.OK);
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(stadiumValidator);
	}
}
