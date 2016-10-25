package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.MatchModel;
import project.models.RoundModel;
import project.repositories.MatchRepository;
import project.repositories.RoundRepository;
import project.services.RoundService;

@Service
public class RoundServiceImp implements RoundService {

	@Autowired
	private RoundRepository roundRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Override
	public RoundModel read(Integer id) {
		if (!roundRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return roundRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!roundRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		roundRepository.delete(id);
	}

	@Override
	public List<RoundModel> getAll() {
		return roundRepository.findAll();
	}

	@Override
	public List<MatchModel> getAllMatches(Integer roundId) {
		if (!roundRepository.exists(roundId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return matchRepository.findByRoundId(roundId);
	}

	@Override
	public MatchModel addMatch(Integer roundId, MatchModel match) {
		if (!roundRepository.exists(roundId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		RoundModel round = roundRepository.findOne(roundId);
		match.setRound(round);
		return matchRepository.save(match);
	}

}
