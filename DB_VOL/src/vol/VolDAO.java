package vol;
import java.sql.*;
import java.util.*;

public class VolDAO {
	private Connection conn;
	
	public VolDAO() {
		conn=DB.connect();
	}
	
	public List<Vol> getAllVols() throws SQLException {
		List<Vol> ls = new ArrayList<>();
		try(
				Statement stmt = conn.createStatement()
		){
			ResultSet rs = stmt.executeQuery("SELECT * FROM vol");
			while(rs.next()) {
				ls.add(
					new Vol(rs.getString("numero_vol"),rs.getString("ville_depart"),rs.getString("ville_arrivee"))
				);
			}
			return ls;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public void ajouterVol(Vol v) throws SQLException {
		try(
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO vol (numero_vol,ville_depart,ville_arrivee) VALUES (?,?,?) ");
		){
			stmt.setString(1,v.getNumeroVol());
			stmt.setString(2,v.getVilleDepart());
			stmt.setString(3,v.getVilleArrive());
			stmt.executeUpdate();
    		System.out.println("vol added");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void modifierDestination(String numeroVol, String nouvelleDestination) throws SQLException {
			try(
				PreparedStatement stmt = conn.prepareStatement("UPDATE vol SET ville_arrivee = ? WHERE numero_vol = ?");
			){
				stmt.setString(1,nouvelleDestination);
				stmt.setString(2,numeroVol);
				if(stmt.executeUpdate()>0) {
					System.out.println("vol modified");
				} else {
					System.out.println("Vol not found");
				}
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
	}
	
	public Vol chercherVol(String numeroVol) throws SQLException {
		try(
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vol WHERE numero_vol = ?");
		){
			stmt.setString(1,numeroVol);
			ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            Vol vol = new Vol();
	            vol.setNumeroVol(rs.getString("numero_vol"));
	            vol.setVilleArrive(rs.getString("ville_depart"));
	            vol.setVilleDepart(rs.getString("ville_arrivee"));
	            return vol;
	        } else {
	            return null;
	        }
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
