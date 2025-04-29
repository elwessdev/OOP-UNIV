package main;

import java.awt.*;
import java.awt.event.*;

public class HelloAWT {
    public static void main(String[] args) {
        // Créer une fenêtre
        Frame fenetre = new Frame("Bienvenue"); //-> set frame title "Bienvenue"

        // Créer les composants
        Label labelMessage = new Label("Tape ton nom et clique !"); //-> create label with text "Tape ton nom et clique !"
        TextField champNom = new TextField(20); //-> create an input of type text with a maximum of 20 characters
        Button boutonAfficher = new Button("Afficher"); //-> create button with content "Afficher"

        // Ajouter les composants à la fenêtre
        fenetre.setLayout(new FlowLayout()); //-> set the layout of Frame "Flow Layout"
        fenetre.add(labelMessage);//-> add the Label "Tape ton nom et clique !" to the "fenetre"
        fenetre.add(champNom);//-> add the Input Text to the "fenetre"
        fenetre.add(boutonAfficher);//-> Add the Button "Afficher" to the "fenetre"

        // Action quand on clique sur le bouton
        boutonAfficher.addActionListener(new ActionListener() { //-> add the "addActionListener" method to "boutonAfficher"
            public void actionPerformed(ActionEvent e) { //-> add the function "actionPerformed" for handle the actions listening
                String nom = champNom.getText(); //-> get the value of Text Input "champNom"
                labelMessage.setText("Bonjour " + nom + " !"); //-> update the content of "labelMessage"
            }
        });

        // Configuration de la fenêtre
        fenetre.setSize(300, 150); //-> set the dimensions of "fenetre"
        fenetre.setVisible(true); //-> show in desktop

        // Fermer la fenêtre proprement
        fenetre.addWindowListener(new WindowAdapter() { //-> add "WindowAdapter" method for handle the actions of "fenetre"
            public void windowClosing(WindowEvent e) { //-> add the function "windowClosing" for handle the windowEvent
                System.exit(0); //-> close the system
            }
        });
    }
}