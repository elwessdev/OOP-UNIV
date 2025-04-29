package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ExempleGridBagLayout extends JFrame {
    private static final long serialVersionUID = 1L;

	@Override
    protected void frameInit() {
        super.frameInit();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Exemple grid bag layout");
        this.getContentPane().setLayout(new GridBagLayout());
        
        int rowIndex = 0;
        addRow(rowIndex++, "Civilité", new JComboBox<String>(new String[] {"Madame", "Monsieur"}));
        addRow(rowIndex++, "Nom", new JTextField());
        addRow(rowIndex++, "Prénom", new JTextField());
        addRow(rowIndex++, "Adresse", new JTextArea(10, 20));
        addButtons(rowIndex++, new JButton("Ok"), new JButton("Annuler"));
        this.pack();
        this.setResizable(false);
    }
    
    private void addRow(int rowIndex, String titre, JComponent component) {
        GridBagConstraints cst = new GridBagConstraints();
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.anchor = GridBagConstraints.NORTH;
        cst.insets = new Insets(5, 20, 5, 20);
        cst.gridy = rowIndex;
        cst.gridx = 0;
        cst.weightx = .3;
        
        JLabel label = new JLabel(titre);
        label.setLabelFor(component);
        this.add(label, cst);
        
        cst.gridx = 1;
        cst.weightx = .7;
        this.add(component, cst);
    }
    
    private void addButtons(int rowIndex, JButton...buttons) {
        JPanel panel = new JPanel();
        for (JButton button : buttons) {
            panel.add(button);
        }

        GridBagConstraints cst = new GridBagConstraints();
        cst.insets = new Insets(5, 10, 0, 0);
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridy = rowIndex;
        cst.gridx = 0;
        cst.gridwidth = 2;
        this.add(panel, cst);
    }
    
    public static void main(String[] args) {
        JFrame window = new ExempleGridBagLayout();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}