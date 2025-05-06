package main;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Pratiquons_interactions {
	public static void main(String[] args) {
        Frame fenetre = new Frame("Formulaire de contact");

        Label labelNom = new Label("Nom: ");
        TextField nom = new TextField(20);
        Label labelEmail = new Label("Email: ");
        TextField email = new TextField(20);
        Button boutonSubmit= new Button("Submit");
        Label error = new Label("");

        
        fenetre.setLayout(new FlowLayout());
        fenetre.add(labelNom);
        fenetre.add(nom);
        fenetre.add(labelEmail);
        fenetre.add(email);
        fenetre.add(boutonSubmit);
        fenetre.add(error);

        boutonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	error.setText("");
                if(nom.getText().length()==0 || email.getText().length()==0) {
                	error.setText("Please fill the inputs");
                	return;
                }
                System.out.println("Nom: "+nom.getText());
                System.out.println("Email: "+email.getText());
                System.out.println("Formulaire envoye avec succes");
            }
        });


        fenetre.setSize(300, 150);
        fenetre.setVisible(true);


        fenetre.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
