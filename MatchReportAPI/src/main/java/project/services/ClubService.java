package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.models.ClubModel;

public interface ClubService {

	public ClubModel create(ClubModel club);

	public ClubModel read(Integer id);

	public void delete(Integer id);

	public Page<ClubModel> getAll(Integer id, String name, Integer idStadium, Pageable pageable);
}
