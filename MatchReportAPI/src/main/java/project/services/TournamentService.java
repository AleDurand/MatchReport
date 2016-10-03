package project.services;

import java.util.List;

import project.models.RoundModel;
import project.models.TournamentModel;

public interface TournamentService {

	public TournamentModel create(TournamentModel tournament);

	public TournamentModel read(Integer id);

	public void delete(Integer id);

	public List<TournamentModel> getAll();

	public List<RoundModel> getAllRounds(Integer tournamentId);
	
	public RoundModel addRound(Integer tournamentId, RoundModel round);

}
