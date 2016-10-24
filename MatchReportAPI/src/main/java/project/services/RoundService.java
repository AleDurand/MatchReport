package project.services;

import java.util.List;

import project.models.MatchModel;
import project.models.RoundModel;

public interface RoundService {

	public RoundModel read(Integer id);

	public void delete(Integer id);

	public List<RoundModel> getAll();

	public List<MatchModel> getAllMatches(Integer roundId);

	public MatchModel addMatch(Integer roundId, MatchModel match);

}
