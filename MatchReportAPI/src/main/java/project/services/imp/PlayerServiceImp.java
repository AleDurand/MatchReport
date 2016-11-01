package project.services.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.PlayerModel;
import project.models.PlayerStatus;
import project.models.QPlayerModel;
import project.repositories.PlayerRepository;
import project.services.PlayerService;

import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class PlayerServiceImp implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public PlayerModel create(PlayerModel player) {
		return playerRepository.save(player);
	}

	@Override
	public PlayerModel read(Integer id) {
		if (!playerRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return playerRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!playerRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		playerRepository.delete(id);
	}

	@Override
	public List<PlayerModel> getAll(Integer id, String firstname, String lastname, Date birthDateBefore, Date birthDateAfter, Integer documentNumber, PlayerStatus status) {
		List<BooleanExpression> expressions = new ArrayList<BooleanExpression>();
		
		QPlayerModel player = QPlayerModel.playerModel;
		BooleanExpression expression1 = (id != null) ? player.id.eq(id) : null;
		expressions.add(expression1);
		
		BooleanExpression expression2 = (firstname != null) ? player.firstname.contains(firstname) : null;
		expressions.add(expression2);
		
		BooleanExpression expression3 = (lastname != null) ? player.lastname.eq(lastname) : null;
		expressions.add(expression3);
		
		BooleanExpression expression4 = (birthDateBefore != null) ? player.birthDate.before(birthDateBefore) : null;
		expressions.add(expression4);
		
		BooleanExpression expression5 = (birthDateAfter != null) ? player.birthDate.after(birthDateAfter) : null;
		expressions.add(expression5);
		
		BooleanExpression expression6 = (documentNumber != null) ? player.documentNumber.eq(documentNumber) : null;
		expressions.add(expression6);
		
		BooleanExpression expression7 = (status != null) ? player.status.eq(status) : null;
		expressions.add(expression7);
		
		BooleanExpression expression = null;
		for (BooleanExpression ex : expressions) {
			if (expression == null) {
				expression = ex;
			} else {
				expression = expression.or(ex);
			}
		}

		return (List<PlayerModel>) playerRepository.findAll(expression);
	}

}
