package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.StadiumModel;
import project.repositories.StadiumRepository;
import project.services.StadiumService;

@Service
public class StadiumServiceImp implements StadiumService {

	@Autowired
	private StadiumRepository stadiumRepository;

	@Override
	public List<StadiumModel> getAll() {
		return stadiumRepository.findAll();
	}

}
