package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.models.ClubModel;

@Repository
public interface ClubRepository extends JpaRepository<ClubModel, Integer> {

}
