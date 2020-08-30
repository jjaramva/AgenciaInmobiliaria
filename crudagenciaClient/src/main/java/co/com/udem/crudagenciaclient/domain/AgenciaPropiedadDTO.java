package co.com.udem.crudagenciaclient.domain;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgenciaPropiedadDTO {

	private Long id;
	private Double areaMetros;
	private int numeroHabitaciones;
	private int numeroBanos;
	private String estadoPropiedad;
	private Double valor;

	@Autowired
	private AgenciaUsuarioDTO usuarioDTO;

}