package arowonaMovieRental;

/*
 * Dipo Arowona
 * MovieRemoved Class
 * Computer Science 12 
 * May 24th, 2019
 * removes movies to the database
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
	//overview
	//enter movie name 
	//enter movie quantity 
	//submit the string and quantity
	// remove info to database
public class MovieRemoved {
	String name;
	int quantity; 
	
	JTextField MovieName = new JTextField();
	JTextField MovieQuantity = new JTextField();
	
	JLabel prompt1 = new JLabel("Enter Movie Name");
	JLabel prompt2 = new JLabel("Enter Movie New MovieQuantity: ");
	JLabel space = new JLabel("  ");
	
	JButton submit =  new JButton("Submit");
	JFrame frame = new JFrame();
	
	JPanel panel = new JPanel();
	
	JButton goBack = new JButton("Go Back");
	public MovieRemoved() {

		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(new Insets(150, 100, 150, 100)));
		
		frame.getContentPane().add(panel);
		frame.setSize(new Dimension(540, 520));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Arowona Movie Rental/EmployeeLogin/EmployeeMenu/RemoveMovie");
		frame.setVisible(true);
		frame.setResizable(false);
		
		prompt1.setForeground(Color.WHITE);
		prompt2.setForeground(Color.WHITE);
		
		MovieName.setPreferredSize(new Dimension(150,30));
		MovieQuantity.setPreferredSize(new Dimension(150,30));//sets size of JTextField
		
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//actionlistener for submit button
				name = MovieName.getText();
				quantity = Integer.parseInt(MovieQuantity.getText());
				if(isValid(name, quantity)) {//calls isvalid method 
					removeMovie(name,quantity);//calls remove method
					JOptionPane.showMessageDialog(frame,"Update is Sucessful!");//output successful message
				}
				else {
					JOptionPane.showMessageDialog(frame,"Update was unsuccessful, either movie name or quantity was wrong");
				}//error check
				
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
	}
	
	public boolean isValid(String name, int quantity) {
		//search through database sequentially to check to see if it valid 
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");//output movie from database
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ArowonaMovieRental_movies");
			
			while(rs.next()) {
				if(rs.getString(1).equals(name)&&rs.getInt(2)>=quantity) {
					return true;
				}
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}
	
	public void removeMovie(String name, int quantity) {
		//remove movie from database
		String update;

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from  arowonamovierental_movies");
			
			
			update = "UPDATE arowonamovierental_movies " + 
					"SET quantity = '"+(quantity)+"'" + 
					"where moviename = '"+name+"' ";
			
			stmt.executeUpdate(update);
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
}
