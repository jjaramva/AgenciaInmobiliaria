package co.com.udem.crudagencia.rest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.crudagencia.dto.AgenciaUsuarioDTO;
import co.com.udem.crudagencia.entities.AgenciaUsuario;
import co.com.udem.crudagencia.entities.User;
import co.com.udem.crudagencia.repositories.AgenciaUsuarioRepository;
import co.com.udem.crudagencia.repositories.UserRepository;
import co.com.udem.crudagencia.util.Constantes;
import co.com.udem.crudagencia.util.ConvertAgenciaUsuario;

@RestController
public class AgenciaUsuarioRestController {

	@Autowired
	private AgenciaUsuarioRepository agenciaUsuaioRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConvertAgenciaUsuario convertAgenciaUsuario;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/usuarios/adicionarUsuario")
	public Map<String, String> adicionarUsuario(@RequestBody AgenciaUsuarioDTO agenciaUsuarioDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			AgenciaUsuario agenciaUsuario = convertAgenciaUsuario.convertToEntity(agenciaUsuarioDTO);
			agenciaUsuaioRepository.save(agenciaUsuario);

			userRepository.save(User.builder().username(Integer.toString(agenciaUsuario.getNumeroIdentificacion()))
					.password(this.passwordEncoder.encode(agenciaUsuario.getPassword()))
					.roles(Arrays.asList("ROLE_USER")).build());

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
		Iterable<AgenciaUsuario> iUsuarios = agenciaUsuaioRepository.findAll();
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

}
