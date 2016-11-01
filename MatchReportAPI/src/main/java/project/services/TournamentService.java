package project.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.models.RoundModel;
import project.models.TournamentModel;

public interface TournamentService {

	public TournamentModel create(TournamentModel tournament);

	public TournamentModel read(Integer id);

	public void delete(Integer id);

	public Page<TournamentModel> getAll(Integer id, Date initDateBefore, Date initDateAfter, Date endDateBefore, Date endDateAfter, Pageable pageable);

	public List<RoundModel> getAllRounds(Integer tournamentId);
	
	public RoundModel addRound(Integer tournamentId, RoundModel round);

}
