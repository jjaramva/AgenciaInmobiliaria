package co.com.udem.crudagencia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgenciaUsuarioDTO {

	private Long id;
	private String nombre;
	private String apellido;
	private String tipoIdentificacion;
	private int numeroIdentificacion;
	private String direccion;
	private String telefono;
	private String email;
	private String password;

}
