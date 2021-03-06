package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import project.models.PlayerModel;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Integer>, QueryDslPredicateExecutor<PlayerModel> {

}
