package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.PlayerModel;
import project.repositories.PlayerRepository;
import project.services.PlayerService;

@Service
public class PlayerServiceImp implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public List<PlayerModel> getAll() {
		return playerRepository.findAll();
	}

}
