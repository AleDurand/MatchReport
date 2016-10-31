package project.services;

import java.util.List;

import project.models.PlayerModel;

public interface PlayerService {

	public PlayerModel create(PlayerModel player);

	public PlayerModel 	read(Integer id);

	public void delete(Integer id);
	
	public List<PlayerModel> getAll(Integer id, String firstname, String lastname, Integer documentNumber, Integer status);

}
