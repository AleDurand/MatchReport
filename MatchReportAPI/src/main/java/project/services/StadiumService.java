package project.services;

import java.util.List;

import project.models.StadiumModel;

public interface StadiumService {

	public StadiumModel create(StadiumModel stadium);

	public StadiumModel read(Integer id);

	public void delete(Integer id);

	public List<StadiumModel> getAll(Integer id, String name);

}
