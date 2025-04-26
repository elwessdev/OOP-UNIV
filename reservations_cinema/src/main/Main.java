package main;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws SQLException {
		
		Scanner scanner = new Scanner(System.in);
		
		SeanceDAO seanceDAO = new SeanceDAO();
		ReservationDAO reservationDAO = new ReservationDAO();
		
		while(true) {
			System.out.println("\n===== MENU CINEMA===");
			System.out.println("1. Afficher les séances disponibles");
			System.out.println("2. Réserver une séance");
			System.out.println("3. Afficher les réservations dn client");
			System.out.println("4. Quitter");
			System.out.print("=> Enter option: ");
			int option = scanner.nextInt();
			System.out.println("");
			
			switch(option) {
				// ------------- 1. Afficher les séances disponibles
				case 1:
					seanceDAO.listerSeances();
					break;
				// ---------------- 2. Réserver une séance
				case 2:
					System.out.print("Enter seance id: ");
					int seance_id = scanner.nextInt();
					
					Seance checkSeance = seanceDAO.getSeanceByID(seance_id);
					
					if(checkSeance==null) {
						System.out.println("Seance not found");
						break;
					}
					
					System.out.print("Enter Client name: ");
					String clientName = scanner.next();
					System.out.print("Enter nombre_places: ");
					int nombre_places = scanner.nextInt();
					
					boolean checkPlaces = seanceDAO.mettreAJourPlaces(checkSeance.getId(), nombre_places);
					if(!checkPlaces) {
						System.out.println("Can't add reservation places greater then places_disponibles");
						break;
					}
					
					Reservation newReserv = new Reservation(
							clientName,
							checkSeance,
							nombre_places
					);
					
					boolean added = reservationDAO.ajouterReservation(newReserv);
					if(!added) {
						System.out.println("Can't add reservation");
						break;
					}
					System.out.println("Reservation added");
					break;
				// ---------------- 3. Afficher les réservations dn client
				case 3:
					System.out.print("Enter Client name: ");
					String clientName1 = scanner.next();
					reservationDAO.getReservationParClient(clientName1);
					break;
				// -------------------- 4. Quitter
				case 4:
					System.out.println("Bye y bahi");
					return;
			}
		}
	}
}
