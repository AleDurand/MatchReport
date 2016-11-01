package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import project.models.StadiumModel;

@Repository
public interface StadiumRepository extends JpaRepository<StadiumModel, Integer>, QueryDslPredicateExecutor<StadiumModel> {

}
