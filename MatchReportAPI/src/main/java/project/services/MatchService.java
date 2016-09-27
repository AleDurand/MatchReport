package project.services;

import java.util.List;

import project.models.ClubModel;
import project.models.MatchModel;
import project.models.PlayerModel;

public interface MatchService {

	public MatchModel create(MatchModel match);

	public MatchModel read(Integer id);

	public void delete(Integer id);
	
	public List<MatchModel> getAll();
	
	public List<PlayerModel> findPlayerbyClub(ClubModel club);

}
