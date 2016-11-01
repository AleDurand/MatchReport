package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import project.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String>, QueryDslPredicateExecutor<UserModel> {

}
