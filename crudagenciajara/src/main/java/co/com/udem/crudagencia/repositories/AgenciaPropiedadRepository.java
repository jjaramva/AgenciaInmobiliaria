package co.com.udem.crudagencia.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.udem.crudagencia.entities.AgenciaPropiedad;

public interface AgenciaPropiedadRepository extends CrudRepository<AgenciaPropiedad, Long> {
	Iterable<AgenciaPropiedad> findBynumeroHabitaciones(int numeroHabitaciones);

	Iterable<AgenciaPropiedad> findByValor(Double valor);

	Iterable<AgenciaPropiedad> findByareaMetros(Double areaMetros);

}
