package main;

public class Reservation {
//	private int id;
	private String nom_client;
	private Seance seance;
	private int num_places;
	
	public Reservation() {}
	

	public Reservation(String nom_client, Seance seance, int num_places) {
//		this.id = id;
		this.nom_client = nom_client;
		this.seance = seance;
		this.num_places = num_places;
	}


	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public int getNum_places() {
		return num_places;
	}

	public void setNum_places(int num_places) {
		this.num_places = num_places;
	}
	
	
//	public int getId() {
//		return id;
//	}


	@Override
	public String toString() {
		return "Reservation [nom_client=" + nom_client + ", seance=" + seance + ", num_places=" + num_places + "]";
	}

}
