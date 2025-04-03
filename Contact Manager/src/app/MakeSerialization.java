package app;

import java.io.*;
import java.nio.file.*;


public class MakeSerialization {
	private final static String filePath = "ContactManager.ser";
	private ContactManager CM;
	
	public MakeSerialization() {}
	
	public void setContactManager(ContactManager CM) {
		this.CM = CM;
	}
	
	public void serialization(){
		try(
				OutputStream f = Files.newOutputStream(Paths.get(filePath));
				ObjectOutputStream obj = new ObjectOutputStream(f);
		) {
			obj.writeObject(this.CM);
			System.out.print("Contact manager serialization");
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ContactManager deserialization() throws ClassNotFoundException {
		try(
				InputStream f = Files.newInputStream(Paths.get(filePath));
				ObjectInputStream obj = new ObjectInputStream(f);
		) {
			return (ContactManager) obj.readObject();
		} catch(IOException | NullPointerException e) {
			System.out.println(e.getMessage());
			return new ContactManager();
		}
	}
}
