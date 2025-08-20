package gt.sgd.sgdbackend.dto;

import java.util.List;

public class JwtAuthenticationResponse {
	private String token;
	private String username;
	private List<String> roles;
	
	public JwtAuthenticationResponse() {}
	/*
	 Su función es permitir que se cree una instancia de la clase sin pasarle ningún dato al momento de construirla.

		Esto es útil cuando:
		
		Usas frameworks como Spring que necesitan instanciar objetos sin argumentos (por ejemplo, al deserializar JSON).
		
		Quieres crear el objeto y luego irle seteando los valores con setters.
	 * */
	
	public JwtAuthenticationResponse(String token, String username, List<String> roles) {
			this.token = token;
			this.username = username;
			this.roles = roles;
	}
	
	/*
	 Ambos son válidos y complementarios. Tener los dos te da flexibilidad:

		El vacío para frameworks y casos donde los datos vienen después.
		
		El parametrizado para construir el objeto de una sola vez.
	 * */

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

}
