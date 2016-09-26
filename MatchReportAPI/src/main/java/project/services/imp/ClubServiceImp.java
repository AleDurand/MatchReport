package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.ClubModel;
import project.repositories.ClubRepository;
import project.services.ClubService;

@Service
public class ClubServiceImp implements ClubService {

	@Autowired
	private ClubRepository clubRepository;

	@Override
	public ClubModel create(ClubModel club) {
		return clubRepository.save(club);
	}

	@Override
	public ClubModel read(Integer id) {
		if (!clubRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return clubRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!clubRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		clubRepository.delete(id);
	}

	@Override
	public List<ClubModel> getAll() {
		return clubRepository.findAll();
	}

}
