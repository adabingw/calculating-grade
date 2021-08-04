import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignUpUser {
	
	public SignUpUser() {
		
		String url = "jdbc:mysql://localhost:3306/grades";
		String username = "root";
		String password = " ";
		
        JPasswordField jpf = new JPasswordField(10);
 	    JLabel jl = new JLabel("Password: ");
		jl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JPasswordField jpf2 = new JPasswordField(10);
 	    JLabel jl2 = new JLabel("Confirm password: ");
		jl2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
 	    
		JTextField username1 = new JTextField(10);
	    JLabel label = new JLabel("Username: ");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JTextField name = new JTextField(10);
	    JLabel label1 = new JLabel("Name: ");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		Object[] message = {
				label1, name,
				label, username1,
				jl, jpf,
				jl2, jpf2,
		};
	    
	    int result = JOptionPane.showConfirmDialog(null, message, 
	             "CREATE NEW ACCOUNT", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
	       String user = username1.getText();
	       String profileName = name.getText();
	       char[] p = jpf.getPassword();
	       String pswrd = String.valueOf(p);
	       char[] p2 = jpf.getPassword();
	       String pswrd2 = String.valueOf(p2);
	       System.out.println("user: " + user);
	       System.out.println("password: " + pswrd);
	       
	       try {	
	    	   Connection connection = DriverManager.getConnection(url, username, password);
	    	   System.out.println("connection success!!");

				String sql1 = "SELECT * FROM user_info WHERE user_id = ?";
		    	PreparedStatement statement1 = connection.prepareStatement(sql1);
		    	statement1.setString(1, user);
		    	   
		    	ResultSet rs = statement1.executeQuery();
		        if (!rs.next() && pswrd.equals(pswrd2)){
		        	String sql = "INSERT INTO user_info (USER_ID, USER_NAME, USER_PASSWORD) VALUES (?, ?, ?)";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, user);
					statement.setString(2, profileName);
					statement.setString(3, pswrd);
					
					int rows = statement.executeUpdate();
					if (rows > 0) {
						System.out.println("A row has been inserted for sign up.");
					}
		        	
		    	   new userMain(user, profileName, pswrd);
		    	   System.out.println("userMain called!");
		        } else if (rs.next()) {
		           JLabel l = new JLabel("Username has already been taken.");
		    	   l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		    	   l.setHorizontalAlignment(SwingConstants.CENTER);
		    	   JOptionPane.showMessageDialog(null, l, "ERROR", JOptionPane.PLAIN_MESSAGE);
		        } else if (!pswrd.equals(pswrd2)) {
		           JLabel l = new JLabel("Passwords do not match");
		    	   l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		    	   l.setHorizontalAlignment(SwingConstants.CENTER);
		    	   JOptionPane.showMessageDialog(null, l, "ERROR", JOptionPane.PLAIN_MESSAGE);
		        }
		    	
			} catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
	        }	   
	    } else {
	    	   new Main();
	       }
	    
	    return; 
    }
}
