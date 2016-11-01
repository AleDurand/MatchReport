package project.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.models.MatchModel;
import project.models.RoundModel;

public interface RoundService {

	public RoundModel read(Integer id);

	public void delete(Integer id);

	public Page<RoundModel> getAll(Integer id, Integer number, String description, Integer tournament, Pageable pageable);

	public List<MatchModel> getAllMatches(Integer roundId);

	public MatchModel addMatch(Integer roundId, MatchModel match);

}
