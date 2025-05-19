package main;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.*;

public class TEST1 extends JFrame{
	private JTextField nomField, prenomField;
	private JPasswordField pwdField;
	
	private JRadioButton hommeBtn,femmeBtn;
	private JComboBox<String> abonnement;
	private JCheckBox natationBox, lectureBox, infoBox;
	private JTextArea bio;
	private JLabel alert;
	private JButton valider;
	
	
	TEST1(){
		setTitle("Test PRoject");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(10,2));
		
		
		nomField = new JTextField();
		prenomField = new JTextField();
		pwdField = new JPasswordField();
		add(new JLabel("Enter Nom: "));
		add(nomField);
		add(new JLabel("Enter Prenom: "));
		add(prenomField);
		add(new JLabel("Enter Password: "));
		add(pwdField);
		
		hommeBtn = new JRadioButton("Homme");
		femmeBtn = new JRadioButton("Femme");
		ButtonGroup rg = new ButtonGroup();
		rg.add(hommeBtn);
		rg.add(femmeBtn);
		add(new JLabel("Sexe :"));
        JPanel sexePanel = new JPanel();
        sexePanel.add(hommeBtn);
        sexePanel.add(femmeBtn);
        add(sexePanel);
        
        
        abonnement = new JComboBox<>(new String[] {"Mensuel", "Trimestriel", "Annuel"});
        add(new JLabel("Type abonnement: "));
        add(abonnement);
        
        natationBox = new JCheckBox("natation");
        lectureBox = new JCheckBox("lectur");
        infoBox = new JCheckBox("info");
        JPanel ac = new JPanel();
        ac.add(natationBox);
        ac.add(lectureBox);
        ac.add(infoBox);
        add(new JLabel("Activite: "));
        add(ac);
        
        bio = new JTextArea();
        add(new JLabel("Biographie"));
        add(bio);
        
        alert = new JLabel("");
        valider = new JButton("Valider");
        add(alert);
        add(valider);
        
        
        FocusAdapter changeBG = new FocusAdapter() {
        	public void focusGained(FocusEvent e) {
        		((JComponent) e.getComponent()).setBackground(Color.red);
        	};
            public void focusLost(FocusEvent e) {
                ((JComponent) e.getComponent()).setBackground(Color.WHITE);
            }
        };
        nomField.addFocusListener(changeBG);
        prenomField.addFocusListener(changeBG);
        pwdField.addFocusListener(changeBG);
        
        
        MouseAdapter hover = new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		JCheckBox cb = (JCheckBox)e.getSource();
        		alert.setText("Vous avez sélectionné : " + cb.getText());
        	};
        	public void mouseExited(MouseEvent e) {
        		alert.setText(" ");
        	};
        };
        
        natationBox.addMouseListener(hover);
        lectureBox.addMouseListener(hover);
        infoBox.addMouseListener(hover);
        
        
        nomField.addKeyListener(new KeyAdapter() {
        	public void keyReleased(KeyEvent e) {
        		if(nomField.getText().matches(".*\\d.*")) {
        			alert.setText("nom don't contain numbers");
        		} else {
        			alert.setText(" ");
        		}
        	}
        });
        
        pwdField.addKeyListener(new KeyAdapter() {
        	public void keyReleased(KeyEvent e) {
        		if(pwdField.getPassword().length < 6) {
        			alert.setText("password must be > 6");
        		} else {
        			alert.setText(" ");
        		}
        	}
        });
        
        
        abonnement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choice = (String) abonnement.getSelectedItem();
				JOptionPane.showMessageDialog(
					null,
                    "Vous avez choisi : " + choice,
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
				);
			}
        });
        
        valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = nomField.getText();
				String prenom = prenomField.getText();
				String pwd = new String(pwdField.getPassword());
				
				String sexe = "";
				if(hommeBtn.isSelected()) sexe = "Homme";
				if(femmeBtn.isSelected()) sexe = "Femme";
				
				String abon = (String) abonnement.getSelectedItem();
				
				String activities = "";
				if(natationBox.isSelected()) activities += "natation, ";
				if(lectureBox.isSelected()) activities += "natation, ";
				if(infoBox.isSelected()) activities += "natation, ";
				
				String bioT = bio.getText();
				
				if (nom.isEmpty() || prenom.isEmpty() || pwd.isEmpty() || sexe.isEmpty() || bioT.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Veuillez remplir tous les champs obligatoires.",
                        "error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
				
				if(!nom.matches("[a-zA-Z]+")||!prenom.matches("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(
                        null,
                        "Le nom et le prénom doivent contenir uniquement des lettres.",
                        "error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
				}
				
				if(pwd.length()<6) {
					JOptionPane.showMessageDialog(
                        null,
                        "Le mot de passe doit contenir au moins 6 caractères.",
                        "error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
				}
				
				String message = "Nom : " + nom + "\n"
                        + "Prénom : " + prenom + "\n"
                        + "Sexe : " + sexe + "\n"
                        + "Abonnement : " + abon + "\n"
                        + "Activités : " + activities + "\n"
                        + "Biographie : " + bioT;
		         JOptionPane.showMessageDialog(
		             null,
		             message,
		             "Résumé de l'inscription",
		             JOptionPane.INFORMATION_MESSAGE
		         );
			}
        });
        
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TEST1();
	}
}
