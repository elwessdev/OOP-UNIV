package main;
import java.awt.*;
import javax.swing.*;

public class Pratiquons extends JFrame {
	private static final long serialVersionUID = 1L;

	protected void frameInit() {
		super.frameInit();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setName("Example box layout");
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
		
		addRow("Number 1",new JTextField());
		addRow("Number 2",new JTextField());
		addRow("",new JComboBox<String>(new String[] {"Soustraction", "Addition", "Multiplication", "Division"}));
		addButtons(new JButton("Soustraction"));
		JTextField resultField = new JTextField();
		resultField.setEditable(false);
		addRow("Resultat",resultField);
		
		
		this.pack();
        this.setSize(400, 250);
        this.setResizable(true);
	}
	
	private void addRow(String titre, JComponent... components) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
		
		JLabel label = new JLabel(titre);
		label.setLabelFor(components[0]);
		panel.add(label);
		
		for(JComponent component:components) {
			panel.add(Box.createHorizontalStrut(10));
			panel.add(component);
		}
		this.add(panel);
	}
	
	private void addButtons(JButton... buttons) {
		
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		JPanel panel = new JPanel(flowLayout);
		for(JButton btn:buttons) {
			panel.add(btn);
		}
		this.add(panel);
	}
	
	public static void main(String[] args) {
		JFrame window = new Pratiquons();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}