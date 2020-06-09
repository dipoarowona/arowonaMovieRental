package arowonaMovieRental;

/*
 * Dipo Arowona
 * Main Page Class
 * Computer Science 12 
 * May 24th, 2019
 * In this class the GUI for the main is made; it also calls the needed classews
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;//needed libraries
import java.io.*;
import java.net.URL;


public class MainPage {
	String[] rand = new String[10];
	public MainPage() throws IOException {
		main(rand);
	}
	public static void main(String[] args) throws IOException {//main method
		JFrame frame = new JFrame();//JFrame object is made
		
		JPanel panel = new JPanel();//JPanel object is made
		panel.setBackground(Color.BLACK);//gui backgroun is black
		frame.getContentPane().add(panel);
		
		panel.setLayout(new GridLayout(7,3));//gui layout is set

		JButton Employee = new JButton("Employee");//button  is made

			
		Employee.addActionListener( 
				new ActionListener() {//action listener class is made for the button calls the employee class
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame.setVisible(false);
						Employee emp = new Employee();
						
					}
				}
			);
	
		JButton Customer = new JButton("Customer");//action listener class is made for the button calls the customer class
		Customer.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
							Customer cust = new Customer(1);
						}
					}
				
				);
		
		JLabel intro = new JLabel("Welcome to Arowona Movie Rental", JLabel.CENTER);//text to output to GUI
		intro.setForeground(Color.RED);//change color of text
		intro.setFont(new Font("Serif", Font.BOLD, 35));//change font and size of text
		
		JLabel instructionsp1 = new JLabel("If you are an employee, press the employee button. ", JLabel.CENTER);
		instructionsp1.setForeground(Color.WHITE);
		
		JLabel instructionsp2 = new JLabel("However, if you are a customer looking to see what movies are available", JLabel.CENTER);
		instructionsp2.setForeground(Color.WHITE);
		
		JLabel instructionsp3 = new JLabel("press the customer button :) ", JLabel.CENTER);
		instructionsp3.setForeground(Color.WHITE);
		
		
		panel.add(intro);
		panel.add(instructionsp1);
		panel.add(instructionsp2);
		panel.add(instructionsp3);
		panel.add(Employee);
		panel.add(Customer);
		
		
		frame.setSize(new Dimension(540,520));//dimensions for the window
		
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when exit button is pressed it closes the program
		
		frame.setTitle("Arowona Movie Rental");//title of program in top bar
		
		frame.setVisible(true);//allows user to see gui
		
		frame.setResizable(false);//does not allow user to change size of window

		
		
	}
}
