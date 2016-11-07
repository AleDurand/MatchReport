package project.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.models.EventModel;
import project.models.MatchModel;
import project.models.MatchStatus;

public interface MatchService {

	public MatchModel read(Integer id);

	public void delete(Integer id);

	public Page<MatchModel> getAll(Integer id, Date dateBefore, Date dateAfter, MatchStatus status, 
			Integer idStadium, Integer idLocal, Integer idVisitor,
			Integer idRound, Pageable pageable);

	public List<EventModel> getAllEvents(Integer matchId);

	public EventModel addEvent(Integer matchId, EventModel event);

}
