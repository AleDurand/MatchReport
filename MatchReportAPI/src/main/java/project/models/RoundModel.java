package project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fecha")
public class RoundModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "numero", nullable = false)
	private Integer number;

	@Column(name = "descripcion", unique = true, nullable = false)
	private String description;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "torneo_id")
	private TournamentModel tournament;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TournamentModel getTournament() {
		return tournament;
	}

	public void setTournament(TournamentModel tournament) {
		this.tournament = tournament;
	}

}
