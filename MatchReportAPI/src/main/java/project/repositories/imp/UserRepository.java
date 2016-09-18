package project.repositories.imp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

}
