package main;

public class Club {
	private String nom;
	private String Prenom;
	private String Password;
	private String Sex;
	private String Type_Abonnement;
	private String Activites;
	private String Biographie;
	
	public Club() {}
	
	public Club(String nom, String prenom, String password, String sex, String type_Abonnement, String activites,
			String biographie) {
		this.nom = nom;
		Prenom = prenom;
		Password = password;
		Sex = sex;
		Type_Abonnement = type_Abonnement;
		Activites = activites;
		Biographie = biographie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getType_Abonnement() {
		return Type_Abonnement;
	}

	public void setType_Abonnement(String type_Abonnement) {
		Type_Abonnement = type_Abonnement;
	}

	public String getActivites() {
		return Activites;
	}

	public void setActivites(String activites) {
		Activites = activites;
	}

	public String getBiographie() {
		return Biographie;
	}

	public void setBiographie(String biographie) {
		Biographie = biographie;
	}

	@Override
	public String toString() {
		return "Club [nom=" + nom + ", Prenom=" + Prenom + ", Password=" + Password + ", Sex=" + Sex
				+ ", Type_Abonnement=" + Type_Abonnement + ", Activites=" + Activites + ", Biographie=" + Biographie
				+ "]";
	}
	
}
