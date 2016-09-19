package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.PlayerModel;
import project.services.PlayerService;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<PlayerModel>> getAll() {
		List<PlayerModel> players = playerService.getAll();
		return new ResponseEntity<>(players, HttpStatus.OK);
	}
}
