package project.services;

import java.util.List;

import project.models.MatchModel;

public interface MatchService {

	public MatchModel create(MatchModel match);

	public MatchModel read(Integer id);

	public void delete(Integer id);
	
	public List<MatchModel> getAll();
	

}
