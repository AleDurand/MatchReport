package project.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "partido")
public class MatchModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha", nullable = false)
	private Date date;

	@JsonFormat(pattern = "hh:mm")
	@Column(name = "hora", nullable = false)
	private Date hour;

	@Column(name = "status", nullable = true)
	private String status;

	@OneToOne
	@JoinColumn(name = "id_cancha")
	private StadiumModel stadium;

	@OneToOne
	@JoinColumn(name = "equipo_local")
	private ClubModel local;

	@OneToOne
	@JoinColumn(name = "equipo_visitante")
	private ClubModel visitor;

	@OneToOne
	@JoinColumn(name = "fecha_id")
	private RoundModel round;

	public MatchModel() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StadiumModel getStadium() {
		return stadium;
	}

	public void setStadium(StadiumModel stadium) {
		this.stadium = stadium;
	}

	public ClubModel getLocal() {
		return local;
	}

	public void setLocal(ClubModel local) {
		this.local = local;
	}

	public ClubModel getVisitor() {
		return visitor;
	}

	public void setVisitor(ClubModel visitor) {
		this.visitor = visitor;
	}

	public RoundModel getRound() {
		return round;
	}

	public void setRound(RoundModel round) {
		this.round = round;
	}

}
