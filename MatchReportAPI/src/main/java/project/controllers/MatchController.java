package project.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import project.models.MatchStatus;
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
	public ResponseEntity<Page<MatchModel>> getAll( // @formatter:off 
			@RequestParam(name = "id", required = false) Integer id,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
			@RequestParam(name = "date-before", required = false) Date dateBefore,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
			@RequestParam(name = "date-after", required = false) Date dateAfter,
			@RequestParam(name = "status", required = false) MatchStatus status,
			@RequestParam(name = "stadium", required = false) Integer idStadium,
			@RequestParam(name = "local", required = false) Integer idLocal,
			@RequestParam(name = "visitor", required = false) Integer idVisitor,
			@RequestParam(name = "club", required = false) Integer idClub,
			@RequestParam(name = "round", required = false) Integer idRound,
			Pageable pageable
	) { // @formatter:on
		Page<MatchModel> matches = matchService.getAll(id, dateBefore, dateAfter, status, idStadium, idLocal, idVisitor, idClub, idRound, pageable);
		return new ResponseEntity<>(matches, HttpStatus.OK);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(matchValidator);
	}

}
