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
	
	public DeleteVal(int c, Object o, Object o1, Object o2, int tableNo, JFrame f, String user_id, String name, String pswrd) {
		JLabel label = new JLabel("Are you sure?");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (JOptionPane.showConfirmDialog(null, label, null,
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			
	        try {	
	    	   Connection connection = DriverManager.getConnection(url, username, password);
	    	   System.out.println("connection success!!");
	    	   
               if (tableNo == 1) {
    	    	   int i = Integer.parseInt((String) o );
		    	   String sql = "DELETE FROM course WHERE course_id = ?";
		    	   PreparedStatement statement = connection.prepareStatement(sql);
		    	   statement.setInt(1, i);
		    	   
					int rows = statement.executeUpdate();
					
		    	   String sql1 = "DELETE FROM unit WHERE course_id = ?";
		    	   PreparedStatement statement1 = connection.prepareStatement(sql1);
		    	   statement1.setInt(1, i);
		    	   
					int rows1 = statement1.executeUpdate();
						
		    	   String sql2 = "DELETE FROM assignments WHERE course_id = ?";
		    	   PreparedStatement statement2 = connection.prepareStatement(sql2);
		    	   statement2.setInt(1, i);
		    	   
					int rows2 = statement2.executeUpdate();
						
					if (rows > 0 || rows1 > 0 || rows2 > 0) {
						new SlideDown(i, 0, 0, tableNo);
						f.setVisible(false);
				 		String m = "Please press refresh to recalculate your grades.";
				 		new ViewDatabase(user_id, name, pswrd, m);
						System.out.println("DELETEVAL");
					}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               } else if (tableNo == 2) {
            	   int i = Integer.parseInt((String) o );
            	   int x = Integer.parseInt((String) o1 );

		    	   String sql = "DELETE FROM unit WHERE unit_id = ? && course_id = ?";
		    	   PreparedStatement statement = connection.prepareStatement(sql);
		    	   statement.setInt(1, x);
		    	   statement.setInt(2, i);
		    	   
		    	   String sql1 = "DELETE FROM assignments WHERE unit_id = ? && course_id = ?";
		    	   PreparedStatement statement1 = connection.prepareStatement(sql1);
		    	   statement1.setInt(1, x);
		    	   statement1.setInt(2, i);
		    	   
					int rows = statement.executeUpdate();
					int rows1 = statement1.executeUpdate();
	
					if (rows > 0) {
						new SlideDown(i, x, 0, tableNo);
						f.setVisible(false);
 			 		    String m = "Please press refresh to recalculate your grades.";
				 		new ViewDatabase(user_id, name, pswrd, m);
					}
					
					if (rows1 > 0) {
						System.out.println("DELETEVAL2");
					}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               } else if (tableNo == 3) {
      	    	   int i = Integer.parseInt((String) o );
    	    	   int x = Integer.parseInt((String) o1 );
    	    	   int j = Integer.parseInt((String) o2 );
		    	   String sql = "DELETE FROM assignments WHERE course_id = ? && unit_id = ? && assign_id = ?";
		    	   PreparedStatement statement = connection.prepareStatement(sql);
		    	   statement.setInt(1, i);
		    	   statement.setInt(2, x);
		    	   statement.setInt(3, j);
		    	   
					int rows = statement.executeUpdate();
	
					if (rows > 0) {
						new SlideDown(i, x, j, tableNo);
						f.setVisible(false);
				 		String m = "Please press refresh to recalculate your grades.";
				 		new ViewDatabase(user_id, name, pswrd, m);
				 		System.out.println("DELETEVAL");
					}
               }
               
			 } catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
	        }
			
		}
	}
}
