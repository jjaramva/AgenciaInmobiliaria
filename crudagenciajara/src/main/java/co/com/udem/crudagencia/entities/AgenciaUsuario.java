package co.com.udem.crudagencia.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class AgenciaUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private String tipoIdentificacion;
	private int numeroIdentificacion;
	private String direccion;
	private String telefono;
	private String email;
	private String password;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "agenciaUsuario")
	private Set<AgenciaPropiedad> agenciaPropiedad;

}