package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class InscriptionEvenementSportif extends JFrame {
	private JTextField nom, age;
	private JPasswordField password;
	private JRadioButton hommeBtn, femmeBtn;
	private JComboBox<String> sport_prefere;
	private JCheckBox endurance, force, vitesse;
	private JTextArea comment;
	private JButton valider;
	private JLabel message;
	InscriptionEvenementSportif(){
		setTitle("Formulaire d'inscription à un événement sportif");
		setLayout(new GridLayout(10,2));
		
		add(new JLabel("Nom"));
		nom = new JTextField();
		add(nom);
		
		add(new JLabel("Age"));
		age = new JTextField();
		add(age);
		
		add(new JLabel("password"));
		password = new JPasswordField();
		add(password);
		
		add(new JLabel("Sexe"));
		hommeBtn = new JRadioButton("Homme");
		femmeBtn = new JRadioButton("Femme");
		ButtonGroup sexeG = new ButtonGroup();
		sexeG.add(hommeBtn);
		sexeG.add(femmeBtn);
		JPanel sexeP = new JPanel();
		sexeP.add(hommeBtn);
		sexeP.add(femmeBtn);
		add(sexeP);
		
		add(new JLabel("Sport préféré"));
		sport_prefere = new JComboBox<>(
			new String[] {"Football","Basket","Natation","Autre"}
		);
		add(sport_prefere);
		
		add(new JLabel("Compétences"));
		endurance = new JCheckBox("Endurance");
		force = new JCheckBox("force");
		vitesse = new JCheckBox("vitesse");
		JPanel com = new JPanel();
		com.add(endurance);
		com.add(force);
		com.add(vitesse);
		add(com);
		
		add(new JLabel("Commentaire"));
		comment = new JTextArea();
		add(comment);
		
		valider = new JButton("Valider");
		add(valider);
		
		message = new JLabel(" ");
		add(message);
		
		// Focus
		FocusListener focus = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				((JComponent) e.getComponent()).setBackground(Color.red);
			}
			@Override
			public void focusLost(FocusEvent e) {
				((JComponent) e.getComponent()).setBackground(Color.WHITE);
			}
		};
		nom.addFocusListener(focus);
		age.addFocusListener(focus);
		password.addFocusListener(focus);
		comment.addFocusListener(focus);
		
		// Mouse
		MouseAdapter hover = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JCheckBox check = (JCheckBox) e.getComponent();
				message.setText("Hover "+check.getText());
			}
			public void mouseExited(MouseEvent e) {
				message.setText(" ");
			}
		};
		endurance.addMouseListener(hover);
		force.addMouseListener(hover);
		vitesse.addMouseListener(hover);
		
		// Check Age
		age.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int ageInt = Integer.parseInt(age.getText());
				if(ageInt < 20) {
					message.setText("age must be > 20");
				} else {
					message.setText(" ");
				}
			}
		});
		
		// Password
		age.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(password.getPassword().length < 6) {
					message.setText("password must be > 6");
				} else {
					message.setText(" ");
				}
			}
		});
		
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomT = nom.getText();
				String aget = age.getText();
				String pwd = new String(password.getPassword());
				
				String sexe = "";
				if(hommeBtn.isSelected()) sexe = "Homme";
				if(femmeBtn.isSelected()) sexe = "Femme";
				
				String sport = (String) sport_prefere.getSelectedItem();
				
				String competence = "";
				if(endurance.isSelected()) competence+="endurance, ";
				if(force.isSelected()) competence+="force, ";
				if(vitesse.isSelected()) competence+="vitesse, ";
				
				String comm = comment.getText();
				
				if(nomT.isEmpty()||aget.isEmpty()||pwd.isEmpty()||sexe.isEmpty()||sport.isEmpty()||comm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields are require", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(nomT.matches(".*\\d.*")) {
					JOptionPane.showMessageDialog(null, "Nom must be don't contain any number");
					return;
				}
				
				if(!nomT.matches("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(null, "Nom must be a characer only");
					return;
				}
				
				System.out.println((Component)e.getSource());
				
				if(Integer.parseInt(aget) < 20) {
					JOptionPane.showMessageDialog(null, "age must be > 20");
					return;
				}
				
				String msg = "Nom: " + nomT + ", Age: " + aget +
						", sexe: " + ", sport: " + sport + ", competence: " + competence + ", comm: " + comm;
				System.out.println(msg);
				JOptionPane.showMessageDialog(
						null,
						msg,
						"Info",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		});

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new InscriptionEvenementSportif();
	}
}
