package main;

public class Livre {
	private String isbn,titre,auteur,annee;
	
	public Livre() {};
	public Livre(String isbn, String titre, String auteur, String annee) {
		this.isbn = isbn;
		this.titre = titre;
		this.auteur = auteur;
		this.annee = annee;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	@Override
	public String toString() {
		return "Livre [isbn=" + isbn + ", titre=" + titre + ", auteur=" + auteur + ", annee=" + annee + "]";
	}
	
}
