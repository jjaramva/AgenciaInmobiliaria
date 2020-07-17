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
import co.com.udem.crudagencia.dto.AgenciaUsuarioDTO;
import co.com.udem.crudagencia.entities.AgenciaUsuario;
import co.com.udem.crudagencia.repositories.AgenciaUsuarioRepository;
import co.com.udem.crudagencia.util.Constantes;
import co.com.udem.crudagencia.util.ConvertAgenciaUsuario;

@RestController
public class AgenciaUsuarioRestController {

	@Autowired
	private AgenciaUsuarioRepository agenciaUsuaioRepositoy;

	@Autowired
	private ConvertAgenciaUsuario convertAgenciaUsuario;

	@PostMapping("/usuarios/adicionarUsuario")
	public Map<String, String> adicionarUsuario(@RequestBody AgenciaUsuarioDTO agenciaUsuarioDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			AgenciaUsuario agenciaUsuario = convertAgenciaUsuario.convertToEntity(agenciaUsuarioDTO);
			agenciaUsuaioRepositoy.save(agenciaUsuario);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Registro insertado exitosamente");
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrio un problema al insertar");
			return response;
		}

	}

	@GetMapping("/usuarios")
	public List<AgenciaUsuarioDTO> getUsuarios() {
		Iterable<AgenciaUsuario> iUsuarios = agenciaUsuaioRepositoy.findAll();
		List<AgenciaUsuario> listaAgenciaUsuarios = new ArrayList<AgenciaUsuario>();
		List<AgenciaUsuarioDTO> listaAgenciaUsuariosDTO = new ArrayList<AgenciaUsuarioDTO>();
		iUsuarios.iterator().forEachRemaining(listaAgenciaUsuarios::add);

		for (int i = 0; i < listaAgenciaUsuarios.size(); i++) {
			try {
				AgenciaUsuarioDTO agenciaUsuarioDTO = convertAgenciaUsuario.convertToDTO(listaAgenciaUsuarios.get(i));
				listaAgenciaUsuariosDTO.add(agenciaUsuarioDTO);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return listaAgenciaUsuariosDTO;
	}

	@GetMapping("/usuarios/{numeroIdentificacion}")
	public AgenciaUsuario buscarUsuario(@PathVariable int numeroIdentificacion) {
		if (agenciaUsuaioRepositoy.findByNumeroIdentificacion(numeroIdentificacion).isPresent()) {
			return agenciaUsuaioRepositoy.findByNumeroIdentificacion(numeroIdentificacion).get();
		} else {
			return null;
		}

	}

}
