package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.TournamentModel;
import project.repositories.TournamentRepository;
import project.services.TournamentService;

@Service
public class TournamentServiceImp implements TournamentService {

	@Autowired
	private TournamentRepository tournamentRepository;

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

}
