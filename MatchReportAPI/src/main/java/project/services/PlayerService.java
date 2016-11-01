package project.services;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.models.PlayerModel;
import project.models.PlayerStatus;

public interface PlayerService {

	public PlayerModel create(PlayerModel player);

	public PlayerModel 	read(Integer id);

	public void delete(Integer id);
	
	public Page<PlayerModel> getAll(Integer id, String firstname, String lastname, Date birthDateBefore, Date birthDateAfter, Integer documentNumber, PlayerStatus status, Pageable pageable);

}
