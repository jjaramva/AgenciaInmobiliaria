package co.com.udem.crudagenciaclient.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
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