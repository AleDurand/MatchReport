package project.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.MatchModel;
import project.models.QRoundModel;
import project.models.RoundModel;
import project.repositories.MatchRepository;
import project.repositories.RoundRepository;
import project.services.RoundService;

import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class RoundServiceImp implements RoundService {

	@Autowired
	private RoundRepository roundRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Override
	public RoundModel read(Integer id) {
		if (!roundRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return roundRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!roundRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		roundRepository.delete(id);
	}

	@Override
	public Page<RoundModel> getAll(Integer id, Integer number, String description, Integer tournament, Pageable pageable) {
		List<BooleanExpression> expressions = new ArrayList<BooleanExpression>();
		
		QRoundModel round = QRoundModel.roundModel;
		BooleanExpression expression1 = (id != null) ? round.id.eq(id) : null;
		expressions.add(expression1);

		BooleanExpression expression2 = (number != null) ? round.number.eq(number) : null;
		expressions.add(expression2);
		
		BooleanExpression expression3 = (description != null) ? round.description.contains(description) : null;
		expressions.add(expression3);
		
		BooleanExpression expression4 = (tournament != null) ? round.tournament.id.eq(tournament) : null;
		expressions.add(expression4);
		
		BooleanExpression expression = null;
		for (BooleanExpression ex : expressions) {
			if (expression == null) {
				expression = ex;
			} else {
				expression = expression.and(ex);
			}
		}
		return roundRepository.findAll(expression, pageable);
	}

	@Override
	public List<MatchModel> getAllMatches(Integer roundId) {
		if (!roundRepository.exists(roundId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return matchRepository.findByRoundId(roundId);
	}

	@Override
	public MatchModel addMatch(Integer roundId, MatchModel match) {
		if (!roundRepository.exists(roundId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		RoundModel round = roundRepository.findOne(roundId);
		match.setRound(round);
		return matchRepository.save(match);
	}

}
