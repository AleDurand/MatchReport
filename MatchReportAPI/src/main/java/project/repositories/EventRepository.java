package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import project.models.EventModel;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Integer>, QueryDslPredicateExecutor<EventModel> {

}
