package project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "club")
public class ClubModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "nombre", nullable = false)
	private String name;

	@Column(name = "direccion", nullable = false)
	private String address;

	@Column(name = "escudo", nullable = true)
	private String image;

	@Column(name = "pagina_web", nullable = true)
	private String url;

	@OneToOne
	@JoinColumn(name = "id_cancha")
	private StadiumModel stadium;

	public ClubModel() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public StadiumModel getStadium() {
		return stadium;
	}

	public void setStadium(StadiumModel stadium) {
		this.stadium = stadium;
	}

}
