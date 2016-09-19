package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.ClubModel;
import project.repositories.ClubRepository;
import project.services.ClubService;

@Service
public class ClubServiceImp implements ClubService {

	@Autowired
	private ClubRepository clubRepository;

	@Override
	public List<ClubModel> getAll() {
		return clubRepository.findAll();
	}

}
