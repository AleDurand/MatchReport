package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.StadiumModel;
import project.services.StadiumService;

@RestController
@RequestMapping(value = "/stadiums")
public class StadiumController {

	@Autowired
	private StadiumService stadiumService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<StadiumModel>> getAll() {
		List<StadiumModel> stadiums = stadiumService.getAll();
		return new ResponseEntity<>(stadiums, HttpStatus.OK);
	}

}
