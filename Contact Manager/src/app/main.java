package app;

import java.util.Scanner;

public class main {
	public static void main(String args[]) throws ClassNotFoundException {
		
		Scanner scanner = new Scanner(System.in);
		
		MakeSerialization CMS = new MakeSerialization();
		ContactManager contactManager = CMS.deserialization();
		CMS.setContactManager(contactManager);
		
		
		while(true) {
			System.out.println("Hello to Contact Manager");
			System.out.println("1. Add Contact");
			System.out.println("2. Modifier Contact");
			System.out.println("3. Supprimer Contact");
			System.out.println("4. Chercher Contact");
			System.out.println("5. Afficher Contact");
			System.out.println("6. Exit");
			System.out.print("-> Choose option: ");
			int option = scanner.nextInt();
			
			switch(option) {
			case 1:
				System.out.println("---- Add Contact ----");
				System.out.print("- Enter nom: ");
				scanner.nextLine();
				String new_nom = scanner.nextLine();
				System.out.print("- Enter Prenom: ");
				String new_prenom = scanner.nextLine();
				System.out.print("- Enter Telehpne: ");
				String new_telephone = scanner.nextLine();
				System.out.print("- Enter Email: ");
				String new_email = scanner.nextLine();
				Contact newC = new Contact(new_nom,new_prenom,new_telephone,new_email);
				contactManager.ajouter(newC);
				CMS.serialization();
				System.out.println("Contact Added");
				System.out.println("------------------");
				break;
			case 2:
				System.out.println("---- Modifier Contact ----");
				System.out.print("Enter Contact Phone: ");
				scanner.nextLine();
				String MC = scanner.nextLine();
				Contact con = contactManager.chercher("phone",MC);
				if(con!=null) {
					System.out.print("- Enter nom: ");
					String m_nom = scanner.nextLine();
					System.out.print("- Enter Prenom: ");
					String m_prenom = scanner.nextLine();
					System.out.print("- Enter Telehpne: ");
					String m_telephone = scanner.nextLine();
					System.out.print("- Enter Email: ");
					String m_email = scanner.nextLine();
					Contact modifC = new Contact(m_nom,m_prenom,m_telephone,m_email);
					boolean saveModif = contactManager.modifier(con.getTelephone(), modifC);
					if(saveModif) {
						System.out.println("Contact Modify");
						CMS.serialization();
					} else {
						System.out.println("Something wrong, Try again");
					}
				} else {
					System.out.println("Contact Not Found");
				}
				System.out.println("------------------");
				break;
			case 3:
				System.out.println("---- Supprimer Contact ----");
				System.out.print("Enter Contact Phone: ");
				scanner.nextLine();
				String SC = scanner.nextLine();
				Contact SumCon = contactManager.chercher("phone",SC);
				if(SumCon!=null) {
					boolean supM = contactManager.supprimer(SumCon);
					if(supM) {
						System.out.println("Contact deleted");
						CMS.serialization();
					} else {
						System.out.println("Something wrong, Try again");
					}
				} else {
					System.out.println("Contact Not Found");
				}
				System.out.println("------------------");
				break;
			case 4:
				System.out.println("---- Chercher Contact ----");
				System.out.println("Choose Type: ");
				System.out.println("1. Nom");
				System.out.println("2. Phone");
				System.out.print("- Choose Option: ");
				scanner.nextLine();
				int chOP = scanner.nextInt();
				if(chOP==1) {
					System.out.println("Enter Nom: ");
					scanner.nextLine();
					String chN = scanner.nextLine();
					Contact contactCher = contactManager.chercher("nom",chN);
					if(contactCher!=null) {
						System.out.println(contactCher);
					} else {
						System.out.println("Contact Not Found");
					}
				}
				if(chOP==2) {
					System.out.println("Enter Phone: ");
					scanner.nextLine();
					String chP = scanner.nextLine();
					Contact contactCher = contactManager.chercher("phone",chP);
					if(contactCher!=null) {
						System.out.println(contactCher);
					} else {
						System.out.println("Contact Not Found");
					}
				}
				System.out.println("------------------");
				break;
			case 5:
				System.out.println("---- Afficher Contact ----");
				contactManager.afficher();
				System.out.println("------------------");
				break;
			case 6:
				System.out.println("Bye BROOOO :)");
                scanner.close();
                return;
			default:
				System.err.println("Invalid option. Please try again.");
			}
		}
	}
}
