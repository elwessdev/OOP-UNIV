package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JFrame implements MouseListener {
    JLabel label;
    JButton btnOK, btnQuit;
    
    Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        
        label = new JLabel("Valider pour afficher");
        btnOK = new JButton("OK");
        btnQuit = new JButton("Quitter");
        
        btnOK.addMouseListener(this);
        btnQuit.addMouseListener(this);

        this.add(label);
        this.add(btnOK);
        this.add(btnQuit);

        this.pack();
        this.setSize(400, 250);
        this.setResizable(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Un button a ete clique !");
        if (e.getSource() == btnOK) {
//            String message = "Quel est votre prenom ?";
            String response = JOptionPane.showInputDialog(
            		this, 
            		"Quel est votre prenom ?", 
            		"Le titre", 
            		JOptionPane.PLAIN_MESSAGE
            );
            if (response != null && response.length() != 0) {
                label.setText("Bonjour " + response);
            }
        } else if (e.getSource() == btnQuit) {
            System.exit(0);
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent arg0) {
    	System.out.println("Presss");
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {}

    public static void main(String[] args) {
        JFrame window = new Main();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
