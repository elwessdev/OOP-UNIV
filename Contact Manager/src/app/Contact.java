package app;

import java.io.Serializable;

public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	
	
	public Contact(String nom, String prenom, String telephone, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Contact [nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", email=" + email + "]";
	}
	
}
