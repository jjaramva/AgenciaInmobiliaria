package co.com.udem.crudagencia.util;

import java.text.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import co.com.udem.crudagencia.dto.AgenciaPropiedadDTO;
import co.com.udem.crudagencia.entities.AgenciaPropiedad;

public class ConvertAgenciaPropiedad {

	@Autowired
	private ModelMapper modelMapper;

	public AgenciaPropiedad convertToEntity(AgenciaPropiedadDTO propiedadDTO) throws ParseException {
		return modelMapper.map(propiedadDTO, AgenciaPropiedad.class);
	}

	public AgenciaPropiedadDTO convertToDTO(AgenciaPropiedad agenciaPropiedad) throws ParseException {
		return modelMapper.map(agenciaPropiedad, AgenciaPropiedadDTO.class);
	}

}
