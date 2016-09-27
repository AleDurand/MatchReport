package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.ClubModel;
import project.models.MatchModel;
import project.models.PlayerModel;
import project.repositories.MatchRepository;
import project.services.MatchService;

@Service
public class MatchServiceImp implements MatchService {

	@Autowired
	private MatchRepository matchRepository;

	@Override
	public MatchModel create(MatchModel match) {
		return matchRepository.save(match);
	}

	@Override
	public MatchModel read(Integer id) {
		if (!matchRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return matchRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!matchRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		matchRepository.delete(id);
	}

	@Override
	public List<MatchModel> getAll() {
		return matchRepository.findAll();
	}

	@Override
	public List<PlayerModel> findPlayerbyClub(ClubModel club) {
		return matchRepository.findPlayerbyClub(club);
	}

}
