package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
	private List<Club> students;
    
    Main(){
    	this.setTitle("liste d'étudiants");
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        
        
        Label nomLabel = new Label("Nom:");
        JTextField nom = new JTextField(20);
        this.add(nomLabel);
        this.add(nom);
        
        Label prenomLabel = new Label("Prenom:");
        JTextField prenom = new JTextField(20);
        this.add(prenomLabel);
        this.add(prenom);

        Label passwordLabel = new Label("Password:");
        JTextField password = new JTextField(20);
        this.add(passwordLabel);
        this.add(password);
        
        Label sexeLabel = new Label("Sexe:");
        JRadioButton homme = new JRadioButton("Homme");
        JRadioButton femme = new JRadioButton("Femme");
        ButtonGroup sexeGroup = new ButtonGroup();
        sexeGroup.add(homme);
        sexeGroup.add(femme);
        this.add(sexeLabel);
        this.add(homme);
        this.add(femme);
        
        Label typeLabel = new Label("Type Abonnement:");
        JComboBox<String> type_abonnement = new JComboBox<>(
    	    new String[] { "Mensuel", "Trimestriel", "Annuel" }
    	);
        this.add(typeLabel);
        this.add(type_abonnement);
        
        Label activiteLabel = new Label("Activite:");
        JCheckBox natation = new JCheckBox("Natation");
        JCheckBox lecture = new JCheckBox("Lecture");
        JCheckBox informatique = new JCheckBox("Informatique");
        this.add(activiteLabel);
        this.add(natation);
        this.add(lecture);
        this.add(informatique);
        
        Label biographieLabel = new Label("Biographie:");
        JTextArea biographie = new JTextArea(5, 20);
        this.add(biographieLabel);
        this.add(biographie);
        
        JButton valider = new JButton("Valider l’inscription");
        JLabel warning = new JLabel("");
        this.add(valider);
        this.add(warning);
        
        
        valider.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("nom: "+nom.getText());
        		System.out.println("nom: "+prenom.getText());
        		System.out.println("nom: "+password.getText());
        	}
        });
        
        
        this.pack();
        this.setSize(400, 600);
        this.setResizable(true);
    }
    
    
    public static void main(String[] args) {
    	JFrame window = new Main();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    
    
    
    
}
