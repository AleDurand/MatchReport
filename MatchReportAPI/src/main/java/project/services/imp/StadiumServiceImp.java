package project.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.QStadiumModel;
import project.models.StadiumModel;
import project.repositories.StadiumRepository;
import project.services.StadiumService;

import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class StadiumServiceImp implements StadiumService {

	@Autowired
	private StadiumRepository stadiumRepository;

	@Override
	public StadiumModel create(StadiumModel stadium) {
		return stadiumRepository.save(stadium);
	}

	@Override
	public StadiumModel read(Integer id) {
		if (!stadiumRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return stadiumRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!stadiumRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		stadiumRepository.delete(id);
	}

	@Override
	public List<StadiumModel> getAll(Integer id, String name) {
		List<BooleanExpression> expressions = new ArrayList<BooleanExpression>();
		
		QStadiumModel stadium = QStadiumModel.stadiumModel;
		BooleanExpression expression1 = (id != null) ? stadium.id.eq(id) : null;
		expressions.add(expression1);

		BooleanExpression expression2 = (name != null) ? stadium.name.contains(name) : null;
		expressions.add(expression2);
		
		BooleanExpression expression = null;
		for (BooleanExpression ex : expressions) {
			if (expression == null) {
				expression = ex;
			} else {
				expression = expression.or(ex);
			}
		}

		return (List<StadiumModel>) stadiumRepository.findAll(expression);
	}

}
