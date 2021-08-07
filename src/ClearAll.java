import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ClearAll {
	
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = "Will@3229967163";
	
	public ClearAll(String user_id, String name, String pswrd, JFrame f) {
		JLabel label = new JLabel("Your grade history will be erased. Continue?");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel l1 = new JLabel("Nothing to clear/error occurred.");
		l1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (JOptionPane.showConfirmDialog(null, label, null,
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

			
			try {	
				Connection connection = DriverManager.getConnection(url, username, password);
				System.out.println("connection success!!");
																
				String sql = "DELETE FROM course WHERE user_id = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, user_id);
												
				String sql1 = "DELETE FROM unit WHERE user_id = ?";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setString(1, user_id);
				
				String sql2 = "DELETE FROM assignments WHERE user_id = ?";
				PreparedStatement statement2 = connection.prepareStatement(sql2);
				statement2.setString(1, user_id);
								
				int rows = statement.executeUpdate();
				int rows1 = statement1.executeUpdate();
				int rows2 = statement2.executeUpdate();
				if (rows2 > 0 || rows1 > 0 || rows > 0) {
					new ViewDatabase(user_id, name, pswrd);
					System.out.println("COURSE - COURSE_ID UPDATE SUCCESSFUL");
					f.setVisible(false);
				} else if (rows == 0 && rows1 == 0 && rows2 == 0) {
					JOptionPane.showMessageDialog(null, l1,
					    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); 
				}						
				
				statement.close();
				statement1.close();
				statement2.close();
				connection.close();
				
			} catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
	        }	   
			
			
		} else {
			JOptionPane.getRootFrame().dispose();   
		}
	}
}
