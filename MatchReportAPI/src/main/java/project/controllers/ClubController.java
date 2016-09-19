package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.ClubModel;
import project.services.ClubService;

@RestController
@RequestMapping(value = "/clubs")
public class ClubController {

	@Autowired
	private ClubService clubService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ClubModel>> getAll() {
		List<ClubModel> clubs = clubService.getAll();
		return new ResponseEntity<>(clubs, HttpStatus.OK);
	}

}
