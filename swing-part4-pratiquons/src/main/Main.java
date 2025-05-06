package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
	private List<Student> students;
	
    JLabel title;
    
    Label nomLabel, ageLabel, villeLabel, errorLabel;
    TextField nomInput, ageInput, villeInput;
    
    Button add, search;
    
    JTextArea listStudent;
    
    
    Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("liste d'étudiants");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        
        nomLabel = new Label("Enter student name: ");
        nomInput = new TextField(20);
        
        ageLabel = new Label("Enter student Age: ");
        ageInput = new TextField(20);
        
        villeLabel = new Label("Enter student Ville: ");
        villeInput = new TextField(20);
        
        errorLabel = new Label("");
        
        add = new Button("Add");
        search = new Button("Search");
        
        listStudent = new JTextArea();
        
        students = new ArrayList<>();
        
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	errorLabel.setText("");
                String nom = nomInput.getText();
                String age = ageInput.getText();
                String ville = villeInput.getText();
                if(nom.length()==0||age.length()==0||ville.length()==0) {
                	errorLabel.setText("All fields require");
                	return;
                }
                for (int i = 0; i < nom.length(); i++) {
                    if (Character.isDigit(nom.charAt(i))) {
                    	errorLabel.setText("name must be don't contain numbers");
                        return;
                    }
                }
                int age_num = Integer.parseInt(age);
                if(age_num<1||age_num>100) {
                	errorLabel.setText("Age must be > 1 and <= 100");
                	return;
                }
                for(Student st:students) {
                	if(st.getNom()==nom||st.getAge()==age_num||st.getVille()==ville) {
                		errorLabel.setText("The students already exist");
                		return;
                	}
                }
                students.add(new Student(nom, age_num, ville));
                errorLabel.setText("Student added!");
                nomInput.setText("");
                ageInput.setText("");
                villeInput.setText("");
                
            }
        });
        
        search.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		errorLabel.setText("");
        		String name = JOptionPane.showInputDialog(
        				Main.this, 
                		"Enter name", 
                		"Student Search", 
                		JOptionPane.PLAIN_MESSAGE
                );
        		if (name != null && name.length()!=0) {
                    for (Student st:students) {
                    	if(st.getNom().equals(name)) {
                    		listStudent.append("Name: "+st.getNom()+"\n");
                    		listStudent.append("Age: "+st.getAge()+"\n");
                    		listStudent.append("Ville: "+st.getVille()+"\n");
                    	}
                    }
                }
        		else {
        			errorLabel.setText("Please enter name for search");
        		}
        	}
        });


        this.add(nomLabel);
        this.add(nomInput);
        this.add(ageLabel);
        this.add(ageInput);
        this.add(villeLabel);
        this.add(villeInput);
        this.add(errorLabel);
        this.add(add);
        this.add(search);
        this.add(listStudent);
        
        this.pack();
        this.setSize(400, 400);
        this.setResizable(true);
    }

    public static void main(String[] args) {
        JFrame window = new Main();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

