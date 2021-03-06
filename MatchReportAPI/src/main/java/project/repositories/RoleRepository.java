package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import project.models.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Integer>, QueryDslPredicateExecutor<RoleModel> {

}
