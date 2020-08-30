package co.com.udem.crudagencia.rest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import co.com.udem.crudagencia.dto.AgenciaPropiedadDTO;
import co.com.udem.crudagencia.entities.AgenciaPropiedad;
import co.com.udem.crudagencia.exception.ResourceNotFoundException;
import co.com.udem.crudagencia.repositories.AgenciaPropiedadRepository;
import co.com.udem.crudagencia.repositories.AgenciaUsuarioRepository;
import co.com.udem.crudagencia.util.Constantes;
import co.com.udem.crudagencia.util.ConvertAgenciaPropiedad;

@RestController
public class AgenciaPropiedadRestController {

	@Autowired
	private AgenciaPropiedadRepository agenciaPropiedadRepository;

	@Autowired
	private ConvertAgenciaPropiedad convertAgenciaPropiedad;

	@Autowired
	private AgenciaUsuarioRepository agenciaUsuarioRepository;

	@PostMapping("/propiedades/addPropiedad/{username}")
	public Map<String, String> addpropiedad(@PathVariable(value = "username") String username,
			@RequestBody AgenciaPropiedadDTO agenciaPropiedadDTO) throws ResourceNotFoundException {
		Map<String, String> response = new HashMap<>();

		try {
			AgenciaPropiedad agenciaPropiedad = convertAgenciaPropiedad.convertToEntity(agenciaPropiedadDTO);
			agenciaUsuarioRepository.findBynumeroIdentificacion(Integer.parseInt(username)).map(usuario -> {
				agenciaPropiedad.setAgenciaUsuario(usuario);
				return agenciaPropiedad;
			}).orElseThrow(() -> new ResourceNotFoundException("Error con usuario"));
			agenciaPropiedadRepository.save(agenciaPropiedad);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Registro insertado exitosamente");
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al insertar");
			return response;
		}

	}

	@GetMapping("/propiedades/numeroh/{numeroHabitaciones}")
	public Iterable<AgenciaPropiedad> getNumHabitaciones(@PathVariable int numeroHabitaciones) {
		return agenciaPropiedadRepository.findBynumeroHabitaciones(numeroHabitaciones);

	}

	@GetMapping("/propiedades/valorp/{valor}")
	public Iterable<AgenciaPropiedad> getValorPropiedad(@PathVariable Double valor) {
		return agenciaPropiedadRepository.findByValor(valor);

	}

	@GetMapping("/propiedades/area/{areaMetros}")
	public Iterable<AgenciaPropiedad> getAreaMetros(@PathVariable Double areaMetros) {
		return agenciaPropiedadRepository.findByareaMetros(areaMetros);

	}

	@GetMapping("/propiedades/usuario/{numeroIdentificacion}")
	public List<AgenciaPropiedadDTO> getPropiedadesUser(@PathVariable int numeroIdentificacion) {
		Iterable<AgenciaPropiedad> iPropieda = agenciaPropiedadRepository.findAll();
		List<AgenciaPropiedad> listaAgenciaPropiedad = new ArrayList<AgenciaPropiedad>();
		List<AgenciaPropiedadDTO> listaAgenciaPropiedadDTO = new ArrayList<AgenciaPropiedadDTO>();
		iPropieda.iterator().forEachRemaining(listaAgenciaPropiedad::add);

		for (int i = 0; i < listaAgenciaPropiedad.size(); i++) {
			if (listaAgenciaPropiedad.get(i).getAgenciaUsuario().getNumeroIdentificacion() == numeroIdentificacion) {
				try {
					AgenciaPropiedadDTO agenciaPropiedadDTO = convertAgenciaPropiedad
							.convertToDTO(listaAgenciaPropiedad.get(i));
					listaAgenciaPropiedadDTO.add(agenciaPropiedadDTO);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		}

		return listaAgenciaPropiedadDTO;
	}

}
