package project.services.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.EventModel;
import project.models.MatchModel;
import project.models.MatchStatus;
import project.models.QMatchModel;
import project.repositories.EventRepository;
import project.repositories.MatchRepository;
import project.services.MatchService;

import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class MatchServiceImp implements MatchService {

	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public MatchModel read(Integer id) {
		if (!matchRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return matchRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!matchRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		matchRepository.delete(id);
	}

	@Override
	public Page<MatchModel> getAll(Integer id, Date dateBefore, Date dateAfter, MatchStatus status,
			Integer idStadium, Integer idLocal, Integer idVisitor,
			Integer idRound, Pageable pageable) {
		
		List<BooleanExpression> expressions = new ArrayList<BooleanExpression>();

		QMatchModel match = QMatchModel.matchModel;
		BooleanExpression expression1 = (id != null) ? match.id.eq(id) : null;
		expressions.add(expression1);
		
		BooleanExpression expression2 = (dateBefore != null) ? match.date.before(dateBefore) : null;
		expressions.add(expression2);
		
		BooleanExpression expression3 = (dateAfter != null) ? match.date.after(dateAfter) : null;
		expressions.add(expression3);
		
		BooleanExpression expression4 = (status != null) ? match.status.eq(status) : null;
		expressions.add(expression4);
		
		BooleanExpression expression5 = (idStadium != null) ? match.stadium.id.eq(idStadium) : null;
		expressions.add(expression5);
		
		BooleanExpression expression6 = (idLocal != null) ? match.local.id.eq(idLocal) : null;
		expressions.add(expression6);
		
		BooleanExpression expression7 = (idVisitor != null) ? match.visitor.id.eq(idVisitor) : null;
		expressions.add(expression7);
		
		BooleanExpression expression8 = (idRound != null) ? match.round.id.eq(idRound) : null;
		expressions.add(expression8);
		
		BooleanExpression expression = null;
		for (BooleanExpression ex : expressions) {
			if (expression == null) {
				expression = ex;
			} else {
				expression = expression.or(ex);
			}
		}
		
		return matchRepository.findAll(expression, pageable);
	}

	@Override
	public List<EventModel> getAllEvents(Integer matchId) {
		if (!matchRepository.exists(matchId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return eventRepository.findByMatchId(matchId);
	}

}
