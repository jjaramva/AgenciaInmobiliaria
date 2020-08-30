package co.com.udem.crudagenciaclient.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.udem.crudagenciaclient.entities.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

	@Query("SELECT u.token FROM UserToken u WHERE u.username = ?1")
	public String obtenerToken(String username);

}
