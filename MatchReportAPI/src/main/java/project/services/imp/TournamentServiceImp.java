package project.services.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.QTournamentModel;
import project.models.RoundModel;
import project.models.TournamentModel;
import project.repositories.RoundRepository;
import project.repositories.TournamentRepository;
import project.services.TournamentService;

import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class TournamentServiceImp implements TournamentService {

	@Autowired
	private TournamentRepository tournamentRepository;

	@Autowired
	private RoundRepository roundRepository;

	@Override
	public TournamentModel create(TournamentModel tournament) {
		return tournamentRepository.save(tournament);
	}

	@Override
	public TournamentModel read(Integer id) {
		if (!tournamentRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return tournamentRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!tournamentRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		tournamentRepository.delete(id);
	}

	@Override
	public Page<TournamentModel> getAll(Integer id, Date initDateBefore, Date initDateAfter, Date endDateBefore, Date endDateAfter, Pageable pageable) {
		List<BooleanExpression> expressions = new ArrayList<BooleanExpression>();

		QTournamentModel tournament = QTournamentModel.tournamentModel;
		BooleanExpression expression1 = (id != null) ? tournament.id.eq(id) : null;
		expressions.add(expression1);
		
		BooleanExpression expression2 = (initDateBefore != null) ? tournament.initDate.before(initDateBefore) : null;
		expressions.add(expression2);
		
		BooleanExpression expression3 = (initDateAfter != null) ? tournament.initDate.after(initDateAfter) : null;
		expressions.add(expression3);
		
		BooleanExpression expression4 = (endDateBefore != null) ? tournament.endDate.before(endDateBefore) : null;
		expressions.add(expression4);
		
		BooleanExpression expression5 = (endDateAfter != null) ? tournament.endDate.after(endDateAfter) : null;
		expressions.add(expression5);
		
		BooleanExpression expression = null;
		for (BooleanExpression ex : expressions) {
			if (expression == null) {
				expression = ex;
			} else {
				expression = expression.or(ex);
			}
		}
		
		return tournamentRepository.findAll(expression, pageable);
	}

	@Override
	public List<RoundModel> getAllRounds(Integer tournamentId) {
		if (!tournamentRepository.exists(tournamentId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return roundRepository.findByTournamentId(tournamentId);
	}

	@Override
	public RoundModel addRound(Integer tournamentId, RoundModel round) {
		if (!tournamentRepository.exists(tournamentId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		TournamentModel tournament = tournamentRepository.findOne(tournamentId);
		round.setTournament(tournament);
		return roundRepository.save(round);
	}

}
