/*
 * Dipo Arowona
 * AddionalEmployee Class
 * Computer Science 12 
 * May 24th, 2019
 * In this class the GUI for the AdditionalEmployee class is made; it takes in information and adds the new employee to the database
 */
package arowonaMovieRental;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdditionalEmployee {
	JLabel prompt = new JLabel("Enter new employee number: ");
	JLabel prompt1 = new JLabel("Enter new employee password:");
	
	JTextField employeeNumber = new JTextField();
	JTextField employeePassword = new JTextField();
	
	JButton submit = new JButton("Submit");
	JButton goBack = new JButton("Go Back");
	
	int en;
	String pass;
	
	
	public AdditionalEmployee() {
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(new Insets(150, 100, 150, 100)));
		
		frame.getContentPane().add(panel);
		frame.setSize(new Dimension(540, 520));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Arowona Movie Rental/EmployeeLogin/EmployeeMenu/AddEmployee");
		frame.setVisible(true);
		frame.setResizable(false);	
		
		prompt.setForeground(Color.WHITE);
		prompt1.setForeground(Color.WHITE);
		
		submit.addActionListener(new ActionListener() {//acctionlistener for the submit button, it checks to see if id is already is use and calls appropriate class
			@Override
			public void actionPerformed(ActionEvent e) {
				en = Integer.parseInt(employeeNumber.getText());
				pass = employeePassword.getText();//sets input from JTextField to the variables
				
				if(isNumberValid(en)) {//checks to see if id already in use
					addEmployee(en, pass);//adds employee to database
					JOptionPane.showMessageDialog(frame, "Update is Successful!");//output message
				}
				else {
					JOptionPane.showMessageDialog(frame, "The employee was not found");//error check
				}
			}
			
		});
		
		goBack.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent a) {
						EmployeeMenu em = new EmployeeMenu();
						frame.setVisible(false);
					}
				}
			);
		
		
		panel.add(prompt);
		panel.add(employeeNumber);
		panel.add(prompt1);
		panel.add(employeePassword);
		panel.add(submit);
		panel.add(goBack);
	}
	public boolean isNumberValid(int num) {
		//checks to see if employee id issued to new employee is already in use
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");//output movie from database
			//establishes connection to database
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ArowonaMovieRental_employee");
			
			while(rs.next()) {//loops through database table to see whether id is already in use
				if(rs.getInt(1)==num) {
					return false;
				}
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return true;
	}
	
	
	public void addEmployee(int number, String password) {//this adds employee id and password to database table
		String add;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");
			Statement stmt = con.createStatement();
	
			add = "insert into ArowonaMovieRental_employee"
					+ "(id, password)"
					+ "values ('"+number+"', '"+password+"')";
			stmt.executeUpdate(add);
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}

