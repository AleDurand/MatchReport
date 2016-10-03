package project.services;

import java.util.List;

import project.models.RoundModel;

public interface RoundService {

	public RoundModel read(Integer id);

	public void delete(Integer id);

	public List<RoundModel> getAll();

}
