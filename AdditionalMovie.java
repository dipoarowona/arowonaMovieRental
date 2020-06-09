/*
 * Dipo Arowona
 * AdditionalMovie Class
 * Computer Science 12 
 * May 24th, 2019
 * adds movies to the database
 */
package arowonaMovieRental;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdditionalMovie {
	String name;
	int quantity; 
	
	JTextField MovieName = new JTextField();
	JTextField MovieQuantity = new JTextField();
	
	JLabel prompt1 = new JLabel("Enter Movie Name");
	JLabel prompt2 = new JLabel("Enter Movie Quantity: ");
	JLabel space = new JLabel("  ");
	
	JButton submit =  new JButton("Submit");
	JButton goBack = new JButton("Go Back");
	
	public AdditionalMovie(){//constructor
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(new Insets(150, 100, 150, 100)));
		
		frame.getContentPane().add(panel);
		frame.setSize(new Dimension(540, 520));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Arowona Movie Rental/EmployeeLogin/EmployeeMenu/AddMovie");
		frame.setVisible(true);
		frame.setResizable(false);
		
		prompt1.setForeground(Color.WHITE);
		prompt2.setForeground(Color.WHITE);
		
		MovieName.setPreferredSize(new Dimension(150,30));
		MovieQuantity.setPreferredSize(new Dimension(150,30));
		
		
		submit.addActionListener(new ActionListener() {//actionlistener for submit button
			public void actionPerformed(ActionEvent e) {
				name = MovieName.getText();
				quantity = Integer.parseInt(MovieQuantity.getText());
				addMovie(name, quantity);//calls add movie method sends name and quantity to it 
				JOptionPane.showMessageDialog(frame,"Update is Successful!");//update message sent
			}
		} 
		);
		
		goBack.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent a) {
						EmployeeMenu em = new EmployeeMenu();
						frame.setVisible(false);
					}
				}
			);
				
		panel.add(prompt1);
		panel.add(MovieName);
		panel.add(prompt2);
		panel.add(MovieQuantity);
		panel.add(space);
		panel.add(submit);
		panel.add(goBack);
		addMovie(name, quantity);
		
	}
	
	public void addMovie(String name, int quantity) {
		//this method adds movies to database table
		String add;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");
			Statement stmt = con.createStatement();
	
			add = "insert into ArowonaMovieRental_movies"
					+ "(movieName, quantity)"
					+ "values ('"+name+"', '"+quantity+"')";
			stmt.executeUpdate(add);
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
	}
}
