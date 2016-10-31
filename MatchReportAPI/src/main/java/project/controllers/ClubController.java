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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.models.ClubModel;
import project.services.ClubService;
import project.validators.ClubValidator;

@RestController
@RequestMapping(value = "/clubs")
public class ClubController {

	@Autowired
	private ClubService clubService;

	@Autowired
	private ClubValidator clubValidator;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<ClubModel> create(@Validated @RequestBody ClubModel club) {
		ClubModel toReturn = clubService.create(club);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ClubModel> read(@PathVariable("id") Integer id) {
		ClubModel toReturn = clubService.read(id);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		clubService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ClubModel>> getAll( // @formatter:off
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "stadium", required = false) Integer idStadium
	) { // @formatter:on
		List<ClubModel> clubs = clubService.getAll(id, name, idStadium);
		return new ResponseEntity<>(clubs, HttpStatus.OK);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(clubValidator);
	}

}
