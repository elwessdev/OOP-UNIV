package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class TEST2 extends JFrame {
	private JTextField nom,prenom;
	private JPasswordField password;
	private JRadioButton hommeBtn,femmeBtn;
	private JComboBox<String> abonnementBox;
	private JCheckBox natation,lecture,info;
	private JTextArea biographie;
	private JButton valider;
	private JLabel warning;
	
	
	TEST2(){
		setTitle("TEST2");
		setLayout(new GridLayout(10,2));
		
		
		add(new JLabel("Nom:"));
		nom = new JTextField();
		add(nom);
		
		add(new JLabel("Prenom:"));
		prenom = new JTextField();
		add(prenom);
		
		add(new JLabel("Password:"));
		password = new JPasswordField();
		add(password);
		
		add(new JLabel("Sexe:"));
		hommeBtn = new JRadioButton("Homme");
		femmeBtn = new JRadioButton("Femme");
		ButtonGroup sexG = new ButtonGroup();
		sexG.add(femmeBtn);
		sexG.add(hommeBtn);
		JPanel sexeP = new JPanel();
		sexeP.add(hommeBtn);
		sexeP.add(femmeBtn);
		add(sexeP);
		
		add(new JLabel("Type Abonnement:"));
		abonnementBox = new JComboBox<>(new String[]{"Mensuel", "Trimestriel", "Annuel"});
		add(abonnementBox);
		
		add(new JLabel("Activite:"));
		natation = new JCheckBox("natation");
		lecture = new JCheckBox("lecture");
		info = new JCheckBox("info");
		JPanel activiteP = new JPanel();
		activiteP.add(natation);
		activiteP.add(lecture);
		activiteP.add(info);
		add(activiteP);
		
		add(new JLabel("Biographie"));
		biographie = new JTextArea();
		add(biographie);
		
		warning = new JLabel(" ");
		add(warning);
		
		valider = new JButton("Valider");
		add(valider);
		
		FocusListener changeBG = new FocusListener() {
			public void focusGained(FocusEvent e) {
				((JComponent) e.getComponent()).setBackground(Color.red);
			}
			public void focusLost(FocusEvent e) {
				((JComponent) e.getComponent()).setBackground(Color.WHITE);
			}
		};
		nom.addFocusListener(changeBG);
		prenom.addFocusListener(changeBG);
		password.addFocusListener(changeBG);
		
		password.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(password.getPassword().length < 6) {
					warning.setText("Password must > 6");
				} else {
					warning.setText(" ");
				}
			}
		});
		
		
		nom.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(nom.getText().matches(".*\\d.*")) {
					warning.setText("Nom don't contain any numbers");
				} else {
					warning.setText(" ");
				}
			}
		});
		
		MouseAdapter hover = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JCheckBox ch = (JCheckBox) e.getSource();
				warning.setText("Hover in check " + ch.getText());
			}
			public void mouseExited(MouseEvent e) {
				warning.setText(" ");
			}
		};
		natation.addMouseListener(hover);
		lecture.addMouseListener(hover);
		info.addMouseListener(hover);
		
		
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomF = nom.getText();
				String prenomF = prenom.getText();
				String pwd = new String(password.getPassword());
				
				String sexe = "";
				if(femmeBtn.isSelected()) sexe = "Homme";
				if(hommeBtn.isSelected()) sexe = "Femme";
				
				String typeAb = (String) abonnementBox.getSelectedItem();
				
				String activite = "";
				if(natation.isSelected()) activite+="natation, ";
				if(lecture.isSelected()) activite+="lecture, ";
				if(info.isSelected()) activite+="info, ";
				
				String bio = biographie.getText();
				
				if(nomF.isEmpty()||prenomF.isEmpty()||pwd.isEmpty()||bio.isEmpty()||sexe.isEmpty()) {
					JOptionPane.showMessageDialog(null,"all fields required","error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(pwd.length() < 6) {
					JOptionPane.showMessageDialog(null,"Password must > 6","error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(nomF.matches(".*\\d.*")) {
					JOptionPane.showMessageDialog(null,"Nom don't contain any numbers","error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!nomF.matches("[a-zA-Z]+")||!prenomF.matches("[a-zA-z]+")) {
					JOptionPane.showMessageDialog(null,"nom must be characters","error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				String message = "Nom : " + nomF + "\n"
                        + "Prénom : " + prenomF + "\n"
                        + "Sexe : " + sexe + "\n"
                        + "Abonnement : " + typeAb + "\n"
                        + "Activités : " + activite + "\n"
                        + "Biographie : " + bio;
	
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
		new TEST2();
	}
}
