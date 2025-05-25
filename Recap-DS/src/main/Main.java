package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame {
	private Connection conn;
	private JTextField titre,auteur,annee;
	private JTextArea zone;
	private JButton ajouter,rechercher,supprimer,afficher;
	Main(){
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RecapDS", "postgres", "root");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
            return;
        }
		
		setTitle("Recap DS");
		setLayout(new GridLayout(10,2));
		
//		add(new JLabel("SBN: "));
//		isbn = new JTextField();
//		add(isbn);
		
		add(new JLabel("Titre: "));
		titre = new JTextField();
		add(titre);
		
		add(new JLabel("Auteur: "));
		auteur = new JTextField();
		add(auteur);
		
		add(new JLabel("Annee: "));
		annee = new JTextField();
		add(annee);
		
		zone = new JTextArea();
		add(zone);
		
		ajouter = new JButton("Ajouter");
		rechercher = new JButton("Rechercher");
		supprimer = new JButton("Supprimer");
		afficher = new JButton("Afficher");
		add(ajouter);
		add(rechercher);
		add(supprimer);
		add(afficher);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				String isbnValue = isbn.getText();
				String TitreValue = titre.getText();
				String AuteurValue = auteur.getText();
				String AnneeValue = annee.getText();
				if(TitreValue.isEmpty()||AuteurValue.isEmpty()||AnneeValue.isEmpty()) {
					JOptionPane.showMessageDialog(null,"All fields are required","Error",JOptionPane.ERROR_MESSAGE);
				}
				if(!AnneeValue.matches(".*\\d.*")||AnneeValue.length()!=4) {
					JOptionPane.showMessageDialog(null,"Annee is not valid","Error",JOptionPane.ERROR_MESSAGE);
				}
				try(
					PreparedStatement ps = conn.prepareStatement("INSERT INTO livres (titre,auteur,annee) VALUES (?,?)");
				){
					ps.setString(1,TitreValue);
	        		ps.setString(2,AuteurValue);
	        		ps.setString(3,AnneeValue);
	        		ps.executeUpdate();
	        		System.out.println("livre added");
				} catch(SQLException e1) {
		            e1.printStackTrace();
				}
			}
		});
	}
	
	
	public static void main(String[] args) {
		new Main();
	}
	
	
	
	
	
	
	
	
}
