package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CarteEtudiant extends JFrame {
	
	private JTextField nom,prenom,numero_etud;
	private JPasswordField password;
	private JComboBox<String> specialite,annee;
	private JRadioButton hommeBtn,femmeBtn;
	private JButton valider;
	private JLabel etiquette_etat;
	
	CarteEtudiant(){
		setTitle("Demande de Cart universitaire");
		setLayout(new GridLayout(10,2));
		
		add(new JLabel("Nom: "));
		nom = new JTextField();
		add(nom);
		
		add(new JLabel("Prenom: "));
		prenom = new JTextField();
		add(prenom);
		
		add(new JLabel("Numero Etudiant: "));
		numero_etud = new JTextField();
		add(numero_etud);
		
		add(new JLabel("Password: "));
		password = new JPasswordField();
		add(password);
		
		add(new JLabel("Specialite: "));
		specialite = new JComboBox<>(new String[] {"Info","Maths","Physique"});
		add(specialite);
		
		add(new JLabel("Annee: "));
		annee = new JComboBox<>(new String[] {"1ere","2eme","3eme","Master"});
		add(annee);
		
		add(new JLabel("Genere: "));
		hommeBtn = new JRadioButton("Homme");
		femmeBtn = new JRadioButton("Femme");
		ButtonGroup genGroup = new ButtonGroup();
		genGroup.add(femmeBtn);
		genGroup.add(hommeBtn);
		JPanel genP = new JPanel();
		genP.add(femmeBtn);
		genP.add(hommeBtn);
		add(genP);
		
		valider = new JButton("Valider");
		add(valider);
		
		etiquette_etat = new JLabel(" ");
		add(etiquette_etat);
		
		// Change Background when hover on Nom, Prenom, Numero Etudiant Fields
		FocusListener changeBG = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				((JComponent) e.getComponent()).setBackground(Color.orange);
			}
			@Override
			public void focusLost(FocusEvent e) {
				((JComponent) e.getComponent()).setBackground(Color.WHITE);
			}
		};
		nom.addFocusListener(changeBG);
		prenom.addFocusListener(changeBG);
		numero_etud.addFocusListener(changeBG);
		
		// Show Dynamique message when Focus on Homme, Femme Radio Box
		MouseAdapter focus = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JRadioButton ch = (JRadioButton) e.getComponent();
				etiquette_etat.setText("Vous avers selectionne: " + ch.getText());
			}
			public void mouseExited(MouseEvent e) {
				etiquette_etat.setText(" ");
			}
		};
		hommeBtn.addMouseListener(focus);
		femmeBtn.addMouseListener(focus);
		
		// Dynamique verification Nom
		nom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String nomT = nom.getText();
				if(nomT.matches(".*\\d.*")) {
					etiquette_etat.setText("Should name don't contain Digits");
				} else {
					etiquette_etat.setText(" ");
				}
			}
		});
		
		// Dynamique verification Numero Etudiant
		numero_etud.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String numeroEtud = numero_etud.getText();
				if(!numeroEtud.matches("[0-9]+")) {
					etiquette_etat.setText("Should Numero Etudiant don't contain Characters");
				} else {
					etiquette_etat.setText(" ");
				}
			}
		});
		
		// Dynamique verification Password
		password.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String passwordT = new String(password.getPassword());
				if(passwordT.length() < 6) {
					etiquette_etat.setText("Password must be greater then 6");
				} else {
					etiquette_etat.setText(" ");
				}
			}
		});
		
		// Dynamique verification Prenom
		prenom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String prenomT = prenom.getText();
				if(prenomT.matches(".*\\d.*")) {
					etiquette_etat.setText("Should Prenom don't contain Digits");
				} else {
					etiquette_etat.setText(" ");
				}
			}
		});
		
		
		// Valider Button For Show Information
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomT = nom.getText();
				String prenomT = prenom.getText();
				String numeroEtud = numero_etud.getText();
				String passwordT = new String(password.getPassword());
				
				String spec = (String) specialite.getSelectedItem();
				
				String ann = (String) annee.getSelectedItem();
				
				String genre = "";
				if(hommeBtn.isSelected()) genre = "Homme";
				if(femmeBtn.isSelected()) genre = "Femme";
				
				// Check All fields are not empty
				if(nomT.isEmpty()||prenomT.isEmpty()||numeroEtud.isEmpty()||passwordT.isEmpty()||spec.isEmpty()||ann.isEmpty()||genre.isEmpty()) {
					JOptionPane.showMessageDialog(null,"All fields required","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// Check Nom don't contain any number
				if(nomT.matches(".*\\d.*")) {
					JOptionPane.showMessageDialog(null,"Should name don't contain Digits","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Check Prenom Don't contain number
				if(prenomT.matches(".*\\d.*")) {
					JOptionPane.showMessageDialog(null,"Should Prenom don't contain Digits","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Check Numero Etudiant don't contain character
				if(!numeroEtud.matches("[1-9]+")) {
					JOptionPane.showMessageDialog(null,"Should Numero Etudiant don't contain Characters","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Check Password greater then 6
				if(passwordT.length() < 6) {
					JOptionPane.showMessageDialog(null,"Password must be greater then 6","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// Iformation Message
				String con = "Nom: "+ nomT + "\n" +
				"Prenom: "+ prenomT + "\n" +
				"Numero Etudiant: "+ numeroEtud + "\n" +
				"Password: "+ passwordT + "\n" +
				"Specialite: "+ spec + "\n" +
				"Annnee: "+ ann + "\n" +
				"Genre: "+ genre;
				
				JOptionPane.showMessageDialog(null,con,"Etudiant Information",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new CarteEtudiant();
	}
}
