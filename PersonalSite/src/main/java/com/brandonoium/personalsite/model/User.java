package com.brandonoium.personalsite.model;

import javax.persistence.*;

/**
 * 
 * @author boium
 *
 */
@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	private String passwdHash;
	private String role;

	public User(long id, String username, String passwdHash, String role) {
		this.id = id;
		this.username = username;
		this.passwdHash = passwdHash;
		this.role = role;
	}

	public User() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswdHash() {
		return passwdHash;
	}

	public void setPasswdHash(String passwdHash) {
		this.passwdHash = passwdHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
