package project.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("TEAM_EVENT")
public class TeamEventModel extends EventModel {

	@OneToOne
	@JoinColumn(name = "club_id")
	private ClubModel team;

	public TeamEventModel() {
		super();
	}

	public ClubModel getTeam() {
		return team;
	}

	public void setTeam(ClubModel team) {
		this.team = team;
	}

}
