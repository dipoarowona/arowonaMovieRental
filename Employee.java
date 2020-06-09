package arowonaMovieRental;

/*
 * Dipo Arowona
 * Employee Class
 * Computer Science 12 
 * May 24th, 2019
 * In this class the GUI for the employee class is made; it takes user input, and processes to see if the values are indeed valid
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

public class Employee {
	//3 labels - employee number , password , register new employee
	JLabel employeeNumberPrompt = new JLabel("Enter Employee Number: ", JLabel.CENTER);
	JLabel employeePasswordPrompt = new JLabel("Enter Employee Password: ", JLabel.CENTER);
	JLabel space = new JLabel("                          ");
	JTextField employeeNumber = new JTextField();//where the user will enter employee number
	JPasswordField employeePassword = new JPasswordField();//where user will enter password
	JButton submit = new JButton("Submit");//button to submit changed
	int num; 
	String pass;//variables to store inputed values
	JButton goBack = new JButton("Go Back");
	
	public Employee() {//constructor 
		
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(new Insets(150, 100, 150, 100)));
		
		frame.getContentPane().add(panel);		
		frame.setSize(new Dimension(540,520));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Arowona Movie Rental/EmployeeLogin");
		frame.setVisible(true);
		frame.setResizable(false);
	
		employeeNumberPrompt.setForeground(Color.WHITE);
		employeePasswordPrompt.setForeground(Color.WHITE);
		
		employeePassword.setPreferredSize(new Dimension(150,30));
		
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
						@Override//actionlistener initializes variables and passes it to isValid method
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							num = Integer.parseInt(employeeNumber.getText());
							pass = employeePassword.getText();
							
							if(isValid(num,pass)) {
								//calls employee menu class - another page
								frame.setVisible(false);
								EmployeeMenu EM = new EmployeeMenu();
							}
							else {
								JOptionPane.showMessageDialog(frame, "Invalid Username or Password!");//error message
							}
							
						}
					}
				);
		
		panel.add(employeeNumberPrompt);
		panel.add(employeeNumber);
		panel.add(employeePasswordPrompt);
		panel.add(employeePassword);
		panel.add(space);
		panel.add(submit);
		panel.add(goBack);
	}
	
	
	public static boolean isValid(int num, String pass) {//searches through sql database to see if id and password are indeed valid
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");//output movie from database
			//establishes a connection to database
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from arowonamovierental_employee");
			//a result set is where the information is temporarily stored and it is from the ArowonaMovieRental_employee table
			while(rs.next()) {//sequentially loops through all rows in the table
				if(rs.getInt(1)==num && rs.getString(2).equals(pass)) {//check to see if valid
					return true;
				}
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}
	
}
