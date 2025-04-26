package main;

import java.sql.Date;
import java.sql.Time;
//import java.util.ArrayList;
//import java.util.List;

public class Seance {
	private int id;
	private String film;
	private Date date;
	private Time heure;
//	private int places;
	private int places_dispo;
//	private List<Reservation> listReservation;
	
	public Seance() {}
	
	public Seance(int id, String film, Date date, Time heur, int places_dispo) {
		this.id = id;
		this.film = film;
		this.date = date;
		this.heure = heure;
//		this.places = places;
		this.places_dispo = places_dispo;
//		listReservation = new ArrayList<>();
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getHeure() {
		return heure;
	}

	public void setHeure(Time heure) {
		this.heure = heure;
	}
//
//	public int getPlaces() {
//		return places;
//	}
//
//	public void setPlaces(int places) {
//		this.places = places;
//	}

	public int getPlaces_dispo() {
		return places_dispo;
	}

	public void setPlaces_dispo(int places_dispo) {
		this.places_dispo = places_dispo;
	}
	
	
//	public List<Reservation> getListReservation() {
//		return listReservation;
//	}
//	
//	public boolean addReservation(Reservation res) {
//		listReservation.add(res);
//		return true;
//	}
	
//	public boolean deleteReservation(Reservation res) {
//		for(Reservation reser:listReservation) {
//			if(reser.getId()!=res.getId()) {
//				listReservation.remove(res);
//				return true;
//			}
//		}
//		return false;
//	}
//
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Seance [film=" + film + ", date=" + date + ", heure=" + heure + ", places_dispo="
				+ places_dispo + "]";
	}

	
	
}
