package project.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.ClubModel;
import project.models.QClubModel;
import project.repositories.ClubRepository;
import project.services.ClubService;

import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class ClubServiceImp implements ClubService {

	@Autowired
	private ClubRepository clubRepository;

	@Override
	public ClubModel create(ClubModel club) {
		return clubRepository.save(club);
	}

	@Override
	public ClubModel read(Integer id) {
		if (!clubRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return clubRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!clubRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		clubRepository.delete(id);
	}

	@Override
	public List<ClubModel> getAll(Integer id, String name, Integer idStadium) {
		List<BooleanExpression> expressions = new ArrayList<BooleanExpression>();
		
		QClubModel club = QClubModel.clubModel;
		BooleanExpression expression1 = (id != null) ? club.id.eq(id) : null;
		expressions.add(expression1);
		
		BooleanExpression expression2 = (name != null) ? club.name.contains(name) : null;
		expressions.add(expression2);
		
		BooleanExpression expression3 = (idStadium != null) ? club.stadium.id.eq(idStadium) : null;
		expressions.add(expression3);
		
		BooleanExpression expression = null;
		for(BooleanExpression ex : expressions){
			if(expression == null){
				expression = ex;
			} else {
				expression = expression.or(ex);
			}
		}
		 
		return (List<ClubModel>) clubRepository.findAll(expression);
	}

}
