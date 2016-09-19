package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.TournamentModel;
import project.services.TournamentService;

@RestController
@RequestMapping(value = "/tournaments")
public class TournamentController {

	@Autowired
	private TournamentService tournamentService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<TournamentModel>> getAll() {
		List<TournamentModel> tournaments = tournamentService.getAll();
		return new ResponseEntity<>(tournaments, HttpStatus.OK);
	}

}