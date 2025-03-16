package app;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

public class ContactManager implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Contact> contacts = new ArrayList<>();

	public ContactManager() {}
	
	public boolean ajouter(Contact contact) {
		if(contacts.contains(contact)) return false;
		for(Contact c:contacts) {
			if(c.getTelephone().equals(contact.getTelephone())) {
				return false;
			}
		}
		contacts.add(contact);
		return true;
	}
	
	public boolean modifier(String phone, Contact newContact) {
		for(Contact c:contacts) {
			if(c.getTelephone().equals(phone)) {
				c=newContact;
				return true;
			}
		}
		return false;
	}
	
	public boolean supprimer(Contact contact) {
		if(!contacts.contains(contact)) return false;
		contacts.remove(contact);
		return true;
	}
	
	public void afficher() {
		if(contacts.size()==0) {
			System.out.println("Nothing");
		}
		for(Contact c:contacts) {
			System.out.println(c);
		}
	}
	
	public Contact chercher(String type, String cr) {
		for(Contact c:contacts) {
			if(type=="phone") {
				if(c.getTelephone().equals(cr)) {
					return c;
				}
			}
			if(type=="nom") {
				if(c.getNom().equals(cr)) {
					return c;
				}
			}
		}
		return null;
	}
}
