package co.com.udem.crudagencia.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AgenciaPropiedad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double areaMetros;
	private int numeroHabitaciones;
	private int numeroBanos;
	private String estadoPropiedad;
	private Double valor;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "us_id", referencedColumnName = "id")
	private AgenciaUsuario agenciaUsuario;

}