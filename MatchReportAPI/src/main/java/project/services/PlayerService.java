package project.services;

import java.util.Date;
import java.util.List;

import project.models.PlayerModel;
import project.models.PlayerStatus;

public interface PlayerService {

	public PlayerModel create(PlayerModel player);

	public PlayerModel 	read(Integer id);

	public void delete(Integer id);
	
	public List<PlayerModel> getAll(Integer id, String firstname, String lastname, Date birthDateBefore, Date birthDateAfter, Integer documentNumber, PlayerStatus status);

}
