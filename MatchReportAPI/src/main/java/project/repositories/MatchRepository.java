package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.models.ClubModel;
import project.models.MatchModel;
import project.models.PlayerModel;

@Repository
public interface MatchRepository extends JpaRepository<MatchModel, Integer> {

	public List<PlayerModel> findPlayerbyClub(ClubModel club);

}
