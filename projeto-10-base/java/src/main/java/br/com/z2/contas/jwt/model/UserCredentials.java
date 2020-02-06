package br.com.z2.contas.jwt.model;

import java.io.Serializable;

public class UserCredentials implements Serializable {

	private static final long serialVersionUID = 5100098041492006992L;
	
	private String username;
	private String password;
	
	public UserCredentials() {
	}
	

	public UserCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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