package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		zone.setLineWrap(true);
		zone.setWrapStyleWord(true);
		zone.setEditable(false);
		
		
		ajouter = new JButton("Ajouter");
		rechercher = new JButton("Rechercher");
		supprimer = new JButton("Supprimer");
		afficher = new JButton("Afficher");
		add(ajouter);
		add(rechercher);
		add(supprimer);
		add(afficher);
		
		add(zone);
		
		
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String TitreValue = titre.getText();
				String AuteurValue = auteur.getText();
				String AnneeValue = annee.getText();
				if(TitreValue.isEmpty()||AuteurValue.isEmpty()||AnneeValue.isEmpty()) {
					JOptionPane.showMessageDialog(null,"All fields are required","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!AnneeValue.matches(".*\\d.*")||AnneeValue.length()!=4) {
					JOptionPane.showMessageDialog(null,"Annee is not valid","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// Check Book
				try (
					ResultSet checkBook = exeSelect("SELECT 1 FROM livres WHERE titre = ?", TitreValue);
				){
					if (checkBook.next()) {
						JOptionPane.showMessageDialog(null,"Book with this title already exist","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch(SQLException eCheckBook) {
					eCheckBook.printStackTrace();
				}
				
				// Add Book
				try{
					int addBook = exeUpdate("INSERT INTO livres (titre,auteur,annee) VALUES (?,?,?)",TitreValue,AuteurValue,Integer.parseInt(AnneeValue));
					if(addBook==0) {
						JOptionPane.showMessageDialog(null,"Something wrong when add book","Error",JOptionPane.ERROR_MESSAGE);
					}
	        		titre.setText(" ");
	        		auteur.setText(" ");
	        		annee.setText(" ");
	        		JOptionPane.showMessageDialog(null, "Book Added", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(SQLException e1) {
		            e1.printStackTrace();
				}
			}
		});
		
		rechercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String titreRecherche = JOptionPane.showInputDialog(
	            		null, 
	            		"Titre chercher", 
	            		"Titre", 
	            		JOptionPane.PLAIN_MESSAGE
	            );
				if (titreRecherche.length()==0) {
					return;
				}
				try(
					ResultSet cherBook = exeSelect("SELECT * FROM livres WHERE titre = ?", titreRecherche);
				) {
					if(cherBook.next()) {
						zone.setText(
								"Title: "+ cherBook.getString("titre") + "\n" +
								"Auteur: " + cherBook.getString("auteur") + "\n" +
								"Annee: " + cherBook.getInt("annee")
						);
						return;
					}
					zone.setText("No book with this name");
				} catch(SQLException e1) {
		            e1.printStackTrace();
				}
			}
		});
		
		supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String titireDel = JOptionPane.showInputDialog(
	            		null, 
	            		"Titre for delete", 
	            		"Titre", 
	            		JOptionPane.PLAIN_MESSAGE
	            );
				if (titireDel.length()==0) {
					return;
				}
				try{
					int delBook = exeUpdate("DELETE FROM livres WHERE titre = ?", titireDel);
					if(delBook==0) {
						JOptionPane.showMessageDialog(null, "No book with this name", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showMessageDialog(null, "Book Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(SQLException e1) {
		            System.out.print(e1);
				}
			}
		});
		
		
		afficher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try(
					ResultSet allBooks = exeSelect("SELECT * FROM livres");
				) {
					boolean hasData = false;
					String all = "";
					while (allBooks.next()) {
		                hasData = true;
		                all += "Title: "+ allBooks.getString("titre") + "\n" +
								"Auteur: " + allBooks.getString("auteur") + "\n" +
								"Annee: " + allBooks.getInt("annee") + "\n-------\n";
		            }

		            if (!hasData) {
		                zone.setText("No books");
		            } else {
		                zone.setText(all);
		            }
				} catch(SQLException e1) {
					System.out.print(e1);
				}
			}
		});

		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public int exeUpdate(String sql, Object... params) throws SQLException {
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			for (int i = 0; i < params.length; i++) {
	            stmt.setObject(i+1, params[i]);
	        }
	        return stmt.executeUpdate();
		}
	}
	
	public ResultSet exeSelect(String sql, Object... params) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		for(int i = 0; i < params.length; i++) {
			stmt.setObject(i+1, params[i]);
		}
		return stmt.executeQuery();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	
	
	
	
	
	
	
}
