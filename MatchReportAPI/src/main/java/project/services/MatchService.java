package project.services;

import java.util.Date;
import java.util.List;

import project.models.MatchModel;
import project.models.MatchStatus;

public interface MatchService {

	public MatchModel read(Integer id);

	public void delete(Integer id);

	public List<MatchModel> getAll(Integer id, Date dateBefore, Date dateAfter, MatchStatus status, 
			Integer idStadium, Integer idLocal, Integer idVisitor,
			Integer idRound);

}
