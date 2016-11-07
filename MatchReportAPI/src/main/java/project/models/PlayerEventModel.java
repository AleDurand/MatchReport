package project.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("PLAYER_EVENT")
public class PlayerEventModel extends TeamEventModel {

	@OneToOne
	@JoinColumn(name = "jugador_id")
	private PlayerModel player;

	public PlayerEventModel() {
		super();
	}

	public PlayerModel getPlayer() {
		return player;
	}

	public void setPlayer(PlayerModel player) {
		this.player = player;
	}

}
