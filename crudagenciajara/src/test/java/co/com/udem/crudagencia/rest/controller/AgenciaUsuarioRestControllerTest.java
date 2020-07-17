package co.com.udem.crudagencia.rest.controller;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import co.com.udem.crudagencia.CrudagenciajaraApplication;
import co.com.udem.crudagencia.dto.AgenciaUsuarioDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudagenciajaraApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgenciaUsuarioRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void adicionarAgenciaUsuarioTest() {
		AgenciaUsuarioDTO agenciaUsuarioDTO = new AgenciaUsuarioDTO();
		agenciaUsuarioDTO.setNombre("PruebaNombre");
		agenciaUsuarioDTO.setApellido("PruebaApellido");
		agenciaUsuarioDTO.setTipoIdentificacion("PruebaTipoIdent");
		agenciaUsuarioDTO.setNumeroIdentificacion(2222);
		agenciaUsuarioDTO.setDireccion("PruebaDireccion");
		agenciaUsuarioDTO.setTelefono("44444");
		agenciaUsuarioDTO.setEmail("prueba@prueba");
		agenciaUsuarioDTO.setPassword("E0xy2");

		ResponseEntity<AgenciaUsuarioDTO> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/usuarios/adicionarUsuario", agenciaUsuarioDTO, AgenciaUsuarioDTO.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());

	}

}
