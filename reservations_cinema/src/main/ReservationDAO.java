package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO {
	private Connection conn;
	
	public ReservationDAO() {
		conn=DB.connect();
	}
	
	public boolean ajouterReservation(Reservation reservation) throws SQLException {
		try(
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservation (nom_client,seance_id,nombre_places) VALUES (?,?,?)");
		){
			stmt.setString(1,reservation.getNom_client());
			stmt.setInt(2,reservation.getSeance().getId());
			stmt.setInt(3,reservation.getNum_places());
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
	
	public void getReservationParClient(String clientName) {
		try(
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reservation WHERE nom_client = ?");
		){
			stmt.setString(1,clientName);
			ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            System.out.println(
	            		"nom_client: "+ rs.getString("nom_client")
	            		+ ", seance_id: " + rs.getInt("seance_id")
	            		+ ", nombre_places: " + rs.getInt("nombre_places")
	            );
	        }
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	};
}
