package project.services;

import java.util.List;

import project.models.TournamentModel;

public interface TournamentService {

	public TournamentModel create(TournamentModel tournament);

	public TournamentModel read(Integer id);

	public void delete(Integer id);

	public List<TournamentModel> getAll();

}
