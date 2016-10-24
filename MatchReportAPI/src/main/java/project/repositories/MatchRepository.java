package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.models.MatchModel;

@Repository
public interface MatchRepository extends JpaRepository<MatchModel, Integer> {

	public List<MatchModel> findByRoundId(Integer id);
}
