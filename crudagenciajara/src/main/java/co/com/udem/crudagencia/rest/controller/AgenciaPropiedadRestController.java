package co.com.udem.crudagencia.rest.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/propiedades/addPropiedad/{id}")
	public Map<String, String> addpropiedad(@PathVariable(value = "id") Long id,
			@RequestBody AgenciaPropiedadDTO agenciaPropiedadDTO) throws ResourceNotFoundException {
		Map<String, String> response = new HashMap<>();

		try {
			AgenciaPropiedad agenciaPropiedad = convertAgenciaPropiedad.convertToEntity(agenciaPropiedadDTO);
			agenciaUsuarioRepository.findById(id).map(usuario -> {
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

}
