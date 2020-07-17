package co.com.udem.crudagencia;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import co.com.udem.crudagencia.dto.AgenciaUsuarioDTO;
import co.com.udem.crudagencia.util.ConvertAgenciaPropiedad;
import co.com.udem.crudagencia.util.ConvertAgenciaUsuario;

@SpringBootApplication
public class CrudagenciajaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudagenciajaraApplication.class, args);
	}

	@Bean
	public ConvertAgenciaUsuario convertAgenciaUsuario() {
		return new ConvertAgenciaUsuario();
	}

	@Bean
	public ConvertAgenciaPropiedad convertAgenciaPropiedad() {
		return new ConvertAgenciaPropiedad();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public AgenciaUsuarioDTO agenciaUsuarioDTO() {
		return new AgenciaUsuarioDTO();
	}

}
