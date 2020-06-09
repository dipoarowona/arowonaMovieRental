package arowonaMovieRental;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ServerTesting {
	public static void main(String[] args) {
		String update;
		int quantity =5;
		String name = "Avengers: Endgame";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojects", "root", "exception");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from  arowonamovierental_movies");
			
			
			update = "UPDATE arowonamovierental_movies" + 
					" SET quantity = '"+quantity+"' "+
					" WHERE moviename = '"+name+"' ";
			
			stmt.executeUpdate(update);
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
}
