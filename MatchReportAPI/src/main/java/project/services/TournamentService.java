package project.services;

import java.util.Date;
import java.util.List;

import project.models.RoundModel;
import project.models.TournamentModel;

public interface TournamentService {

	public TournamentModel create(TournamentModel tournament);

	public TournamentModel read(Integer id);

	public void delete(Integer id);

	public List<TournamentModel> getAll(Integer id, Date initDateBefore, Date initDateAfter, Date endDateBefore, Date endDateAfter);

	public List<RoundModel> getAllRounds(Integer tournamentId);
	
	public RoundModel addRound(Integer tournamentId, RoundModel round);

}
