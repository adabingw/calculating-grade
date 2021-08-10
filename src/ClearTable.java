import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ClearTable {
	
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = " ";
	
	public ClearTable (String user_id, String name, String pswrd, JFrame f) {
		Object[] options = {"COURSE",
                "UNIT",
                "ASSIGNMENT",
		"CANCEL"
                };
		JLabel label = new JLabel("Select table to clear");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		int n = JOptionPane.showOptionDialog(null, label, null,
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		if (n == 0) {
			
			JLabel l = new JLabel("This action cannot be reversed. Continue?");
			l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			l.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel l1 = new JLabel("Nothing to clear/error occurred.");
			l1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			
			if (JOptionPane.showConfirmDialog(null, l, "WARNING",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
		            try {	
						Connection connection = DriverManager.getConnection(url, username, password);
						System.out.println("connection success!!");
																		
						String sql = "DELETE FROM course WHERE user_id = ?";
						PreparedStatement statement = connection.prepareStatement(sql);
						statement.setString(1, user_id);
										
						int rows = statement.executeUpdate();
						if (rows > 0) {
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("COURSE - COURSE_ID UPDATE SUCCESSFUL");
							f.setVisible(false);
						} else if (rows == 0) {
							JOptionPane.showMessageDialog(null, l1,
							    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); 
						}						
						statement.close();
						connection.close();
						
					} catch (SQLException e) {
						System.out.println("& i oop");
						e.printStackTrace();
			        }	   
						
			} else {
				JOptionPane.getRootFrame().dispose();   
			}
			
		} else if (n == 1) {

			JLabel l = new JLabel("This action cannot be reversed. Continue?");
			l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			l.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel l1 = new JLabel("Nothing to clear/error occurred.");
			l1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			
			if (JOptionPane.showConfirmDialog(null, l, "WARNING",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
	            try {	
					Connection connection = DriverManager.getConnection(url, username, password);
					System.out.println("connection success!!");
																	
					String sql = "DELETE FROM unit WHERE user_id = ?";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, user_id);
									
					int rows = statement.executeUpdate();
					if (rows > 0) {
						new ViewDatabase(user_id, name, pswrd);
						System.out.println("COURSE - COURSE_ID UPDATE SUCCESSFUL");
						f.setVisible(false);
					} else if (rows == 0) {
						JOptionPane.showMessageDialog(null, l1,
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); 
					}						
					statement.close();
					connection.close();
					
				} catch (SQLException e) {
					System.out.println("& i oop");
					e.printStackTrace();
		        }
				
			} else {
				JOptionPane.getRootFrame().dispose();   
			}
			
		} else if (n == 2) {
			
			JLabel l = new JLabel("This action cannot be reversed. Continue?");
			l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			l.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel l1 = new JLabel("Nothing to clear/error occurred.");
			l1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			
			if (JOptionPane.showConfirmDialog(null, l, "WARNING",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
	            try {	
					Connection connection = DriverManager.getConnection(url, username, password);
					System.out.println("connection success!!");
																	
					String sql = "DELETE FROM assignment WHERE user_id = ?";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, user_id);
									
					int rows = statement.executeUpdate();
					if (rows > 0) {
						new ViewDatabase(user_id, name, pswrd);
						System.out.println("COURSE - COURSE_ID UPDATE SUCCESSFUL");
						f.setVisible(false);
					} else if (rows == 0) {
						JOptionPane.showMessageDialog(null, l1,
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); 
					}						
					statement.close();
					connection.close();
					
				} catch (SQLException e) {
					System.out.println("& i oop");
					e.printStackTrace();
		        }
				
			} else {
				JOptionPane.getRootFrame().dispose();   
			}
			
		} else if (n == 3) {
			JOptionPane.getRootFrame().dispose();
		}
	}
}
