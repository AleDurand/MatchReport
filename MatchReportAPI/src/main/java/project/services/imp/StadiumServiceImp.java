package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.StadiumModel;
import project.repositories.StadiumRepository;
import project.services.StadiumService;

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
	public List<StadiumModel> getAll() {
		return stadiumRepository.findAll();
	}

}
