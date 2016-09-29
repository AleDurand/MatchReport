package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.RoundModel;
import project.models.TournamentModel;
import project.repositories.RoundRepository;
import project.repositories.TournamentRepository;
import project.services.TournamentService;

@Service
public class TournamentServiceImp implements TournamentService {

	@Autowired
	private TournamentRepository tournamentRepository;

	@Autowired
	private RoundRepository roundRepository;

	@Override
	public TournamentModel create(TournamentModel tournament) {
		return tournamentRepository.save(tournament);
	}

	@Override
	public TournamentModel read(Integer id) {
		if (!tournamentRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return tournamentRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!tournamentRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		tournamentRepository.delete(id);
	}

	@Override
	public List<TournamentModel> getAll() {
		return tournamentRepository.findAll();
	}

	@Override
	public List<RoundModel> getAllRounds(Integer tournamentId) {
		if (!tournamentRepository.exists(tournamentId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return roundRepository.findByTournamentId(tournamentId);
	}

	@Override
	public List<RoundModel> getAllMatches(Integer tournamentId, Integer roundId) {
		if (!tournamentRepository.exists(tournamentId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		if (!roundRepository.exists(roundId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return roundRepository.findByTournamentId(tournamentId);
	}

}
