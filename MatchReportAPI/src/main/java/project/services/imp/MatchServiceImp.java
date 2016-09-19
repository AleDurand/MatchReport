package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.MatchModel;
import project.repositories.MatchRepository;
import project.services.MatchService;

@Service
public class MatchServiceImp implements MatchService {

	@Autowired
	private MatchRepository matchRepository;

	@Override
	public List<MatchModel> getAll() {
		return matchRepository.findAll();
	}

}
