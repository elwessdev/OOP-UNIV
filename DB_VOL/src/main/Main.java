package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	private Connection conn;
	
	public Main() {
		conn=DB.connect();
	}
	
    public void addUser(String username, String email) throws SQLException {
    	try (
    			PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username,email) VALUES (?,?)");
        	) {
        		ps.setString(1,username);
        		ps.setString(2,email);
        		ps.executeUpdate();
        		System.out.println("user added");
        } catch (SQLException e) {
//            System.out.println(e.getMessage());
        	System.out.println("user already exist");
        }
    }
    
    public void showUsers() {
    	try (
        		Statement stmt = conn.createStatement()
        	) {
        	
            	ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
	            while (rs.next()) {
	                int id = rs.getInt("user_id");
	                String name = rs.getString("username");
	                String email = rs.getString("email");
	                System.out.println(id + ": " + name + ", email: " + email);
	            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String args[]) throws SQLException {
    	Main m1 = new Main();
    
    	m1.addUser("iyaddddh", "iyadh@iyadh.ddcom");
    	
    	m1.showUsers();
    }
}