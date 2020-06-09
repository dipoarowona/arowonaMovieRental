package arowonaMovieRental;

/*
 * Dipo Arowona
 * EmployeeMenu Class
 * Computer Science 12 
 * May 24th, 2019
 * In this class the GUI for the employeeMenu class is made; it outputs 5 options and send the user to another page based on the option clicked
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EmployeeMenu {

	
	JButton ViewMovies = new JButton("View Movies");
	JButton AddMovie = new JButton ("Add Movie");//buttons made
	JButton RemoveMovie = new JButton("Remove Movie");
	JButton AddEmployee = new JButton("Add Employee");
	JButton RemoveEmployee = new JButton("Remove Employee");
	JButton goBack = new JButton("Go Back");
	public EmployeeMenu() {
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(200, 200, 400, 150));
		
		frame.getContentPane().add(panel);
		frame.setSize(new Dimension(540, 520));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		frame.setTitle("Arowona Movie Rental/EmployeeLogin/EmployeeMenu");
		frame.setVisible(true);
		frame.setResizable(false);
		
		ViewMovies.addActionListener(new ActionListener() {//actionlistener for view movies button
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Customer display = new Customer(0);//calls first half of customers class as it displays the movies available
			}
			}
		);
		
		
		AddMovie.addActionListener(new ActionListener() {
			@Override//actionlistener for add movies button
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				AdditionalMovie addMovie = new AdditionalMovie();
				//calls AdditionalMovie class and sets current window to not be seen
			}
			}
		 );
		
		
		RemoveMovie.addActionListener(new ActionListener() {//actionlistener for remove movies button
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MovieRemoved removeMovie = new MovieRemoved();
				//calls MovieRemoved class and sets current window to not be seen
			}
			}
		 );
		
		AddEmployee.addActionListener(new ActionListener() {//actionlistener for add employee button
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				AdditionalEmployee addEmployee = new AdditionalEmployee();
				//calls AdditionalEmployee class and sets current window to not be seen
			}
			}
		 );
		RemoveEmployee.addActionListener(new ActionListener() {
			@Override//actionlistener for remove employee button
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				EmployeeRemoved removeEmployed = new EmployeeRemoved();
				//calls EmployeeRemoved class and sets current window to not be seen
			}
			}
		 );
		
		goBack.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent a) {
						Employee e = new Employee();
						frame.setVisible(false);
					}
				}
			);
		panel.add(ViewMovies);
		panel.add(AddMovie);
		panel.add(RemoveMovie);
		panel.add(AddEmployee);
		panel.add(RemoveEmployee);
		panel.add(goBack);
	}
// 
}
