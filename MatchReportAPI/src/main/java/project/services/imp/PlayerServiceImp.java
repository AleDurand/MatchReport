package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.PlayerModel;
import project.repositories.PlayerRepository;
import project.services.PlayerService;

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
	public List<PlayerModel> getAll() {
		return playerRepository.findAll();
	}

}
