package arowonaMovieRental;

/*
 * Dipo Arowona
 * EmployeeRemoved Class
 * Computer Science 12 
 * May 24th, 2019
 * removes employees to the database
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EmployeeRemoved {
	JLabel prompt = new JLabel("Enter employee number you wish to remove: ");
	
	JTextField employeeNumber = new JTextField();
	
	JButton submit = new JButton("Submit");
	JButton goBack = new JButton("Go Back");
	
	int en;
	JFrame frame = new JFrame();
	
	JPanel panel = new JPanel();
	public EmployeeRemoved() {//constructor
		
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(new Insets(150, 100, 150, 100)));
		
		frame.getContentPane().add(panel);
		frame.setSize(new Dimension(540, 520));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Arowona Movie Rental/EmployeeLogin/EmployeeMenu/RemoveEmployee");
		frame.setVisible(true);
		frame.setResizable(false);	
		
		prompt.setForeground(Color.WHITE);
		
		submit.addActionListener(new ActionListener() {//actionlistener for the submit button 
			@Override
			public void actionPerformed(ActionEvent e) {
				en = Integer.parseInt(employeeNumber.getText());
				
				if(isValid(en)) {//check to see if the employee id is valid
					removeEmployee(en);//calls removeEmployee method
					JOptionPane.showMessageDialog(frame, "Update is Successful!");//success message displayed
				}
				else {
					JOptionPane.showMessageDialog(frame, "The employee was not found");//error check message displayed
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
		panel.add(submit);
		panel.add(goBack);
	
	
	}
	
	
	public boolean isValid(int num) {
		//check to to see if employee number is valid so that they can be removed
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");//output movie from database
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from arowonamovierental_employee");
			
			while(rs.next()) {
				if(rs.getInt(1)==num) {
					return true;
				}
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}
	
	
	public void removeEmployee(int num) {//removes employee from database
		String remove;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");
			Statement stmt = conn.createStatement();
			
			remove = "delete from arowonamovierental_employee"
					+ " where id = '"+num+"' ";
			
			stmt.executeUpdate(remove);
			
			
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
}

