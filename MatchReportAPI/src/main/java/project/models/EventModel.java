package project.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "evento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminador", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("EVENT")
public class EventModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "partido_id", nullable = false)
	private MatchModel match;

	@Column(name = "tipo", nullable = false)
	private EventType type;

	@Column(name = "minuto", nullable = false)
	private Integer minute;

	@Column(name = "minuto_extra")
	private Integer extraMinute;

	public EventModel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MatchModel getMatch() {
		return match;
	}

	public void setMatch(MatchModel match) {
		this.match = match;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getExtraMinute() {
		return extraMinute;
	}

	public void setExtraMinute(Integer extraMinute) {
		this.extraMinute = extraMinute;
	}

}
