package project.services;

import java.util.List;

import project.models.ClubModel;

public interface ClubService {

	public ClubModel create(ClubModel club);

	public ClubModel read(Integer id);

	public void delete(Integer id);

	public List<ClubModel> getAll(Integer id, String name, Integer idStadium);
}
