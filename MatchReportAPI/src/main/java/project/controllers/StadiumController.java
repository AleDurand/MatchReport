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

import project.models.StadiumModel;
import project.services.StadiumService;
import project.validators.StadiumValidator;

@RestController
@RequestMapping(value = "/stadiums")
public class StadiumController {

	@Autowired
	private StadiumService stadiumService;

	@Autowired
	private StadiumValidator stadiumValidator;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<StadiumModel> create(@Validated @RequestBody StadiumModel stadium) {
		StadiumModel toReturn = stadiumService.create(stadium);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<StadiumModel> read(@PathVariable("id") Integer id) {
		StadiumModel toReturn = stadiumService.read(id);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		stadiumService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<StadiumModel>> getAll( // @formatter:off
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "name", required = false) String name			
	) { // @formatter:on
		List<StadiumModel> stadiums = stadiumService.getAll(id, name);
		return new ResponseEntity<>(stadiums, HttpStatus.OK);
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(stadiumValidator);
	}

}
