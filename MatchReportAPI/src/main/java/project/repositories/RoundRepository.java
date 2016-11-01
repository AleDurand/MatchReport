package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import project.models.RoundModel;

@Repository
public interface RoundRepository extends JpaRepository<RoundModel, Integer>, QueryDslPredicateExecutor<RoundModel> {

	public List<RoundModel> findByTournamentId(Integer id);
}
