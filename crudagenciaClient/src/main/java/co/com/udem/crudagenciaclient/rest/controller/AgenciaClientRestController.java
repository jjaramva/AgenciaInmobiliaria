package co.com.udem.crudagenciaclient.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import co.com.udem.crudagenciaclient.domain.AgenciaPropiedadDTO;
import co.com.udem.crudagenciaclient.domain.AutenticationRequestDTO;
import co.com.udem.crudagenciaclient.domain.AutenticationResponseDTO;
import co.com.udem.crudagenciaclient.entities.UserToken;
import co.com.udem.crudagenciaclient.repositories.UserTokenRepository;
import co.com.udem.crudagenciaclient.util.Constantes;

@RestController
public class AgenciaClientRestController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UserTokenRepository userTokenRepository;

	@Autowired
	UserToken userToken;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@PostMapping("/autenticar")
	public String autenticar(@RequestBody AutenticationRequestDTO autenticationRequestDTO) {
		ServiceInstance serviceInstance = loadBalancer.choose("agenciaJara");
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/auth/signin";
		ResponseEntity<String> postResponse = restTemplate.postForEntity(baseUrl, autenticationRequestDTO,
				String.class);
		Gson g = new Gson();
		AutenticationResponseDTO autenticationResponseDTO = g.fromJson(postResponse.getBody(),
				AutenticationResponseDTO.class);
		userToken.setUsername(autenticationResponseDTO.getUsername());
		userToken.setToken(autenticationResponseDTO.getToken());
		userTokenRepository.save(userToken);
		return autenticationResponseDTO.getToken();

	}

	@PostMapping("/propiedades/addPropiedad/{username}")
	public Map<String, String> addPropiedadClient(@PathVariable String username,
			@RequestBody AgenciaPropiedadDTO agenciaPropiedadDTO) {
		Map<String, String> response = new HashMap<>();
		ServiceInstance serviceInstance = loadBalancer.choose("agenciaJara");
		String baseUrl = serviceInstance.getUri().toString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token = userTokenRepository.obtenerToken(username);

		headers.set("Authorization", "Bearer " + token);
		HttpEntity<AgenciaPropiedadDTO> entity = new HttpEntity<>(agenciaPropiedadDTO, headers);
		Map<String, String> userM = new HashMap<>();
		userM.put("username", username);

		ResponseEntity<String> responseApi = restTemplate.exchange(baseUrl + "/propiedades/addPropiedad/{username}",
				HttpMethod.POST, entity, String.class, userM);

		if (responseApi.getStatusCode() == HttpStatus.OK) {
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Registro insertado exitosamente");
			return response;
		} else {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al insertar");
			return response;
		}

	}

}
