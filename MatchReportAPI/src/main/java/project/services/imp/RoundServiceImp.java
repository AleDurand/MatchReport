package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.RoundModel;
import project.repositories.RoundRepository;
import project.services.RoundService;

@Service
public class RoundServiceImp implements RoundService {

	@Autowired
	private RoundRepository roundRepository;

	@Override
	public RoundModel create(RoundModel round) {
		return roundRepository.save(round);
	}

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
	public List<RoundModel> getAll() {
		return roundRepository.findAll();
	}

}
