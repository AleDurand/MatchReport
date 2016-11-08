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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name = "evento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminador", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("EVENT")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "discriminator")
@JsonSubTypes({ // @formatter:off
	@Type(name = "EVENT", value = EventModel.class),
	@Type(name = "PLAYER_EVENT", value = PlayerEventModel.class),
	@Type(name = "TEAM_EVENT", value = TeamEventModel.class) 
}) // @formatter:on
public class EventModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "discriminador", insertable = false, updatable = false, nullable = false)
	private String discriminator;

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

	public String getDiscriminator() {
		return discriminator;
	}

	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
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
