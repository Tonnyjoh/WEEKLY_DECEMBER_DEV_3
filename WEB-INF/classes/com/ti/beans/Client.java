package com.ti.beans;

public class Client {

	private String nom;
	private String email;

	private String mdpass;
	private long id = 0;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMdpass() {
		return mdpass;
	}

	public void setMdpass(String mdpass) {
		this.mdpass = mdpass;
	}

}
