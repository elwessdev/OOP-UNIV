package vol;
import java.sql.SQLException;
import java.util.Scanner;

public class MainVol {
	
	public static boolean isValidVolNumber(String str) {
        if (str.length() < 3) return false;
        if (!Character.isDigit(str.charAt(0)) || !Character.isDigit(str.charAt(1))) {
        	return false;
        }
        for (int i = 2; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) return false;
        }
        return true;
    }
	
	public static void main(String args[]) throws SQLException {
		VolDAO v1 = new VolDAO();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("\n*************** MENU *****************");
			System.out.println("1. Afficher tous les vols");
			System.out.println("2. Ajouter un nouveau vol");
			System.out.println("3. Modifier la destination d’un vol");
			System.out.println("4. Chercher");
			System.out.println("5. Quitter");
			System.out.print("=> Enter option: ");
			int option = scanner.nextInt();
			System.out.println("********************************\n");
			
			switch(option) {
				case 1:
					for(Vol vol:v1.getAllVols()) {
						System.out.println(vol);
					}
					break;
				case 2:
					String newNumeroVol = "";
					do {
						System.out.print("-> Enter Numero Vol: ");
						newNumeroVol = scanner.next();
					} while(!isValidVolNumber(newNumeroVol));
					System.out.print("-> Enter Ville Depart: ");
					String newVilleDepart = scanner.next();
					System.out.print("-> Enter Ville Arrive: ");
					String newVilleArrive = scanner.next();
					Vol newVol = new Vol(newNumeroVol,newVilleDepart,newVilleArrive);
					v1.ajouterVol(newVol);
					break;
				case 3:
					System.out.print("-> Enter Numero Vol: ");
					String numeroVol = scanner.next();
					System.out.print("-> Enter nouvelle Destination: ");
					String nouvelleDestination = scanner.next();
					v1.modifierDestination(numeroVol, nouvelleDestination);
					break;
				case 4:
					System.out.print("-> Enter Numero Vol: ");
					String numeroVolChercher = scanner.next();
					System.out.println(v1.chercherVol(numeroVolChercher));
					break;
				case 5:
					System.out.println("BYeeeeeeeeeeeeeee");
					return;
				default:
					System.out.println("Please enter valid option");
			}
			
		}
	}
}
