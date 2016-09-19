package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.TournamentModel;
import project.repositories.TournamentRepository;
import project.services.TournamentService;

@Service
public class TournamentServiceImp implements TournamentService {

	@Autowired
	private TournamentRepository tournamentRepository;

	@Override
	public List<TournamentModel> getAll() {
		return tournamentRepository.findAll();
	}

}
