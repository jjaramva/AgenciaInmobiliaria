package co.com.udem.crudagencia.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import co.com.udem.crudagencia.entities.AgenciaUsuario;

public interface AgenciaUsuarioRepository extends CrudRepository<AgenciaUsuario, Long> {
	Optional<AgenciaUsuario> findBynumeroIdentificacion(int numeroIdentificacion);

}
