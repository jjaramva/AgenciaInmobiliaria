package co.com.udem.crudagencia.util;

import java.text.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import co.com.udem.crudagencia.dto.AgenciaUsuarioDTO;
import co.com.udem.crudagencia.entities.AgenciaUsuario;

public class ConvertAgenciaUsuario {

	@Autowired
	private ModelMapper modelMapper;

	public AgenciaUsuario convertToEntity(AgenciaUsuarioDTO usuarioDTO) throws ParseException {
		return modelMapper.map(usuarioDTO, AgenciaUsuario.class);
	}

	public AgenciaUsuarioDTO convertToDTO(AgenciaUsuario agenciaUsuario) throws ParseException {
		return modelMapper.map(agenciaUsuario, AgenciaUsuarioDTO.class);
	}

}
