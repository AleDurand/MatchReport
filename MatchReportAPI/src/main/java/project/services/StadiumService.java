package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.models.StadiumModel;

public interface StadiumService {

	public StadiumModel create(StadiumModel stadium);

	public StadiumModel read(Integer id);

	public void delete(Integer id);

	public Page<StadiumModel> getAll(Integer id, String name, Pageable pageable);

}
