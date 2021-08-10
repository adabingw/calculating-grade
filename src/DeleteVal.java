import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class DeleteVal {
	
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = "root";
	
	public DeleteVal(int c, Object o, int tableNo, JFrame f, String user_id, String name, String pswrd) {
		JLabel label = new JLabel("Are you sure?");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (JOptionPane.showConfirmDialog(null, label, null,
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			
	        try {	
	    	   Connection connection = DriverManager.getConnection(url, username, password);
	    	   System.out.println("connection success!!");
	    	   
               if (tableNo == 1) {
		    	   String sql = "DELETE FROM course WHERE course_name = ?";
		    	   PreparedStatement statement = connection.prepareStatement(sql);
		    	   statement.setString(1, (String) o);
		    	   
					int rows = statement.executeUpdate();
	
					if (rows > 0) {
						f.setVisible(false);
						new ViewDatabase(user_id, name, pswrd);
						System.out.println("DELETEVAL");
					}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               } else if (tableNo == 2) {
            	   if (c == 2) {
            		   int i = Integer.valueOf((String) o);
			    	   String sql = "DELETE FROM unit WHERE unit_w = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setInt(1, i);
			    	   
						int rows = statement.executeUpdate();
		
						if (rows > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("DELETEVAL");
						}
            	   } else if (c == 3) {
            		   int i = Integer.valueOf((String) o);
			    	   String sql = "DELETE FROM unit WHERE unit_final = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setInt(1, i);
			    	   
						int rows = statement.executeUpdate();
		
						if (rows > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("DELETEVAL");
						}
            	   } else if (c == 5) {
			    	   String sql = "DELETE FROM unit WHERE unit_name = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setString(1, (String) o);
			    	   
						int rows = statement.executeUpdate();
		
						if (rows > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("DELETEVAL");
						}
            	   }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               } else if (tableNo == 3) {
            	   if (c == 3) {
            		   int i = Integer.valueOf((String) o);
			    	   String sql = "DELETE FROM assignments WHERE assign_mark = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setInt(1, i);
			    	   
						int rows = statement.executeUpdate();
		
						if (rows > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("DELETEVAL");
						}
            	   } else if (c == 6) {
			    	   String sql = "DELETE FROM assignments WHERE assign_name = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setString(1, (String) o);
			    	   
						int rows = statement.executeUpdate();
		
						if (rows > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("DELETEVAL");
						}
            	   }
               }
               
			 } catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
	        }
			
		}
	}
}
