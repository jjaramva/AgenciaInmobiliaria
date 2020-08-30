package co.com.udem.crudagenciaclient.domain;

public class AutenticationRequestDTO {

	private String username;
	private String password;

	public AutenticationRequestDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AutenticationRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}