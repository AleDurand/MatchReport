package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.models.TournamentModel;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentModel, Integer> {

}
