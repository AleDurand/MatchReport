package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.MatchModel;
import project.services.MatchService;

@RestController
@RequestMapping(value = "/matches")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<MatchModel>> getAll() {
		List<MatchModel> matches = matchService.getAll();
		return new ResponseEntity<>(matches, HttpStatus.OK);
	}

}
