package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InscriptionClub extends JFrame {
    private JTextField nomField, prenomField;
    private JPasswordField mdpField;

    private JRadioButton hommeBtn, femmeBtn;
    private JComboBox<String> abonnementBox;
    private JCheckBox natationBox, lectureBox, infoBox;
    private JTextArea bioArea;
    private JLabel statusLabel;
    private JButton validerBtn;

    public InscriptionClub() {
        setTitle("Inscription Club");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2));

        // Champs texte et mot de passe
        nomField = new JTextField();
        prenomField = new JTextField();
        mdpField = new JPasswordField();

        // Boutons radio Sexe
        hommeBtn = new JRadioButton("Homme");
        femmeBtn = new JRadioButton("Femme");
        ButtonGroup bg = new ButtonGroup();
        bg.add(hommeBtn);
        bg.add(femmeBtn);

        // Combobox Abonnement
        abonnementBox = new JComboBox<>(
            new String[] { "Mensuel", "Trimestriel", "Annuel" }
        );

        // Checkboxes Activités
        natationBox = new JCheckBox("Natation");
        lectureBox  = new JCheckBox("Lecture");
        infoBox     = new JCheckBox("Informatique");

        // Text area Biographie
        bioArea = new JTextArea(3, 20);

        // Label de statut et bouton Valider
        statusLabel = new JLabel(" ");
        validerBtn  = new JButton("Valider");

        // Ajout des composants
        add(new JLabel("Nom :"));
        add(nomField);
        add(new JLabel("Prénom :"));
        add(prenomField);
        add(new JLabel("Mot de passe :"));
        add(mdpField);

        add(new JLabel("Sexe :"));
        JPanel sexePanel = new JPanel();
        sexePanel.add(hommeBtn);
        sexePanel.add(femmeBtn);
        add(sexePanel);

        add(new JLabel("Type d'abonnement :"));
        add(abonnementBox);

        add(new JLabel("Activités :"));
        JPanel actPanel = new JPanel();
        actPanel.add(natationBox);
        actPanel.add(lectureBox);
        actPanel.add(infoBox);
        add(actPanel);

        add(new JLabel("Biographie :"));
        add(new JScrollPane(bioArea));

        add(validerBtn);
        add(statusLabel);

        // Listeners Focus
        FocusListener fl = new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                ((JComponent) e.getComponent()).setBackground(Color.YELLOW);
            }
            public void focusLost(FocusEvent e) {
                ((JComponent) e.getComponent()).setBackground(Color.WHITE);
            }
        };
        nomField.addFocusListener(fl);
        prenomField.addFocusListener(fl);
        mdpField.addFocusListener(fl);

        // Listeners Mouse pour checkboxes
        MouseAdapter ml = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                JCheckBox cb = (JCheckBox) e.getSource();
                statusLabel.setText("Vous avez sélectionné : " + cb.getText());
            }
            public void mouseExited(MouseEvent e) {
                statusLabel.setText(" ");
            }
        };
        natationBox.addMouseListener(ml);
        lectureBox.addMouseListener(ml);
        infoBox.addMouseListener(ml);

        // Listeners Key pour validations en live
        nomField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (nomField.getText().matches(".*\\d.*")) {
                    statusLabel.setText("Le nom ne doit pas contenir de chiffres !");
                } else {
                    statusLabel.setText(" ");
                }
            }
        });
        mdpField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (mdpField.getPassword().length < 6) {
                    statusLabel.setText("Mot de passe trop court !");
                } else {
                    statusLabel.setText(" ");
                }
            }
        });

        // ActionListener sur JComboBox
        abonnementBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String choix = (String) abonnementBox.getSelectedItem();
                JOptionPane.showMessageDialog(
                    null,
                    "Vous avez choisi : " + choix
                );
            }
        });

        // ActionListener sur bouton Valider
        validerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom    = nomField.getText().trim();
                String prenom = prenomField.getText().trim();
                String mdp    = new String(mdpField.getPassword());

                String sexe = "";
                if (hommeBtn.isSelected()) sexe = "Homme";
                else if (femmeBtn.isSelected()) sexe = "Femme";

                String abonnement = (String) abonnementBox.getSelectedItem();

                String activites = "";
                if (natationBox.isSelected()) activites += "Natation ";
                if (lectureBox.isSelected())  activites += "Lecture ";
                if (infoBox.isSelected())     activites += "Informatique ";

                String bio = bioArea.getText().trim();

                // Validations
                if (nom.isEmpty() || prenom.isEmpty() || mdp.isEmpty() || sexe.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Veuillez remplir tous les champs obligatoires.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                if (!nom.matches("[a-zA-Z]+") || !prenom.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Le nom et le prénom doivent contenir uniquement des lettres.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                if (mdp.length() < 6) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Le mot de passe doit contenir au moins 6 caractères.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Affichage du résumé
                String message = "Nom : " + nom + "\n"
                               + "Prénom : " + prenom + "\n"
                               + "Sexe : " + sexe + "\n"
                               + "Abonnement : " + abonnement + "\n"
                               + "Activités : " + activites + "\n"
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
        new InscriptionClub();
    }
}
