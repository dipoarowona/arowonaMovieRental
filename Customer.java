
/*
 * Dipo Arowona
 * customer Class
 * Computer Science 12 
 * May 24th, 2019
 * In this class the GUI for the customer class is made; it takes user input, and outputs movies available from database
 */

package arowonaMovieRental;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//needed libraries
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Customer {
	static JLabel movieName;
	static JLabel quantity;
	JLabel prompt = new JLabel("Enter Movie Name: ");
	JLabel prompt1= new JLabel("Enter Movie Quantity: ");
	JLabel prompt2= new JLabel("Enter Your Name: ");
	JTextField nameofMovie= new JTextField();
	JTextField yourname= new JTextField();
	JTextField movieQuantity= new JTextField();
	JFrame frame = new JFrame();
	int quantityint=0;
	JPanel panel = new JPanel();
	JButton submit = new JButton("Submit");
	JButton goBack = new JButton("Go Back");
	
	public Customer(int x) {//constructor
		
		panel.setBackground(Color.BLACK);
		panel.setLayout(new GridLayout(10,10));
		
		
		frame.getContentPane().add(panel);
		frame.setSize(new Dimension(540, 520));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Arowona Movie Rental/Customer");
		frame.setVisible(true);

		 nameofMovie.setPreferredSize(new Dimension(150,30));
		 yourname.setPreferredSize(new Dimension(150,30));
		 movieQuantity.setPreferredSize(new Dimension(150,30));
		
		try {//database connection is established
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");//output movie from database
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ArowonaMovieRental_movies");
			
			while(rs.next()) {//loops through ArowonaMovieRental_movies table and outputs it to user
				movieName = new JLabel(rs.getString(1));//this stores the movie name of the row in the variable movieName
				quantity = new JLabel(Integer.toString(rs.getInt(2)));//this stores the movie quantity of the row into the variable quantity
				
				movieName.setForeground(Color.WHITE);
				quantity.setForeground(Color.WHITE);
				
				panel.add(movieName);
				panel.add(quantity);
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		
		if(x==0) {
			panel.add(goBack);
			goBack.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent a) {
							EmployeeMenu em = new EmployeeMenu();
							frame.setVisible(false);
						}
					}
				);
		}
		if(x==1) {//adds movie checkout feature for customers only if x equals 1
			prompt.setForeground(Color.WHITE);
			prompt1.setForeground(Color.WHITE);
			prompt2.setForeground(Color.WHITE);
			panel.add(prompt);//adds places for the user to input their name, movi3 being checked out, and quantity
			panel.add(nameofMovie);
			panel.add(prompt1);
			panel.add(movieQuantity);
			panel.add(prompt2);
			panel.add(yourname);
			panel.add(submit);
			panel.add(goBack);
			
			goBack.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent a) {
							try {
								MainPage mp = new MainPage();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							frame.setVisible(false);
						}
					}
				);
			
			submit.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent a) {
							String add, update;
							try {
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");
								Statement stmt = con.createStatement();
								ResultSet rs = stmt.executeQuery("select * from  arowonamovierental_movies");
								
									//adds client name, movie they checked out and quantity to a database using mysql
								add = "insert into ArowonaMovieRental_Customer"
										+ "(name, moviename, quantity)"
										+ "values ('"+yourname.getText()+"','"+nameofMovie.getText()+"', '"+Integer.parseInt(movieQuantity.getText())+"')";
								
								
								while(rs.next()) {
									if(nameofMovie.getText().equals(rs.getString(1))) {
										quantityint = rs.getInt(2);
										break;
									}
								}
								
								update = "UPDATE arowonamovierental_movies " + 
										"SET quantity = '"+(quantityint-Integer.parseInt(movieQuantity.getText()))+"'" + 
										"where moviename = '"+nameofMovie.getText()+"' ";
								stmt.execute(add);
								stmt.execute(update);
								JOptionPane.showMessageDialog(frame, "Update is Successful!");
								
								
							}
							catch(Exception exc) {
								exc.printStackTrace();
							}
						}
					}
				);
					
		}
		
	}

	

}

