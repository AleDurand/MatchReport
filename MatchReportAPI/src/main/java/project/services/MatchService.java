package project.services;

import java.util.List;

import project.models.MatchModel;

public interface MatchService {

	public MatchModel read(Integer id);

	public void delete(Integer id);

	public List<MatchModel> getAll(Integer id, Integer status,
			Integer idStadium, Integer idLocal, Integer idVisitor,
			Integer idRound);

}
