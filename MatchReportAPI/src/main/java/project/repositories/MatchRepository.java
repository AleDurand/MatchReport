package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import project.models.MatchModel;

@Repository
public interface MatchRepository extends JpaRepository<MatchModel, Integer>, QueryDslPredicateExecutor<MatchModel> {

	public List<MatchModel> findByRoundId(Integer id);
}
