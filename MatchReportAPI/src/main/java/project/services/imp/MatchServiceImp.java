package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.MatchModel;
import project.repositories.MatchRepository;
import project.services.MatchService;

@Service
public class MatchServiceImp implements MatchService {

	@Autowired
	private MatchRepository matchRepository;

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
	public List<MatchModel> getAll(Integer id, Integer status,
			Integer idStadium, Integer idLocal, Integer idVisitor,
			Integer idRound) {
		return matchRepository.findAll();
	}

}
