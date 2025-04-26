package main;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceDAO {
	private Connection conn;
	
	public SeanceDAO() {
		conn=DB.connect();
	}
	
	public void listerSeances() throws SQLException {
		try(
			Statement stmt = conn.createStatement();
		){
			ResultSet rs = stmt.executeQuery("SELECT * FROM seance");
			while(rs.next()) {
				System.out.println("ID: "+rs.getInt("id") + ", Film: "+rs.getString("film")
				+ ", date_seance: "+rs.getDate("date_seance")+ ", heure: "+rs.getTime("heure")
				+ ", places_disponibles: "+rs.getInt("places_disponibles"));
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	};
	
	public boolean mettreAJourPlaces(int id, int nombre_places) throws SQLException{
		try(
			PreparedStatement stmt = conn.prepareStatement("UPDATE seance SET places_disponibles = places_disponibles - ? WHERE id = ? AND places_disponibles > ? ");
		){
			stmt.setInt(1,nombre_places);
			stmt.setInt(2,id);
			stmt.setInt(3,nombre_places);
			if(stmt.executeUpdate()>0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	};
	
	public Seance getSeanceByID(int id) {
		try(
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM seance WHERE id = ?");
			){
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Seance s1 = new Seance(
						rs.getInt("id"),
						rs.getString("film"),
						rs.getDate("date_seance"),
						rs.getTime("heure"),
						rs.getInt("places_disponibles")
					);
					return s1;
				}
				return null;
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		return null;
	}
	
	
}
