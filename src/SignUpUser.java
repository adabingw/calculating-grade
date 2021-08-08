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
	
	protected JOptionPane getOptionPane(JComponent parent) {
        	JOptionPane pane = null;
        	if (!(parent instanceof JOptionPane)) {
            		pane = getOptionPane((JComponent)parent.getParent());
        	} else {
            		pane = (JOptionPane) parent;
        	}
        	return pane;
    	}
	
	
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
	    
        final JButton okay = new JButton("Ok");
        okay.setBackground(Color.WHITE);
        okay.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(okay);
            }
        });
        okay.setEnabled(false);
        
        final JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.WHITE);
        cancel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(cancel);
            }
        });
        
        name.getDocument().addDocumentListener(new DocumentListener() {
            protected void update() {
                okay.setEnabled(name.getText().length() > 0 && username1.getText().length() > 0 && jpf.getPassword().length > 0 && jpf2.getPassword().length > 0);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });
        
        username1.getDocument().addDocumentListener(new DocumentListener() {
            protected void update() {
                okay.setEnabled(name.getText().length() > 0 && username1.getText().length() > 0 && jpf.getPassword().length > 0 && jpf2.getPassword().length > 0);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });
        
        jpf.getDocument().addDocumentListener(new DocumentListener() {
            protected void update() {
                okay.setEnabled(name.getText().length() > 0 && username1.getText().length() > 0 && jpf.getPassword().length > 0 && jpf2.getPassword().length > 0);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });
        
        jpf2.getDocument().addDocumentListener(new DocumentListener() {
            protected void update() {
                okay.setEnabled(name.getText().length() > 0 && username1.getText().length() > 0 && jpf.getPassword().length > 0 && jpf2.getPassword().length > 0);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });
        
        int value = JOptionPane.showOptionDialog(
       	     null,
       	     message,
       	     "CREATE NEW ACCOUNT",
       	     JOptionPane.YES_NO_OPTION, 
       	     JOptionPane.QUESTION_MESSAGE, 
       	     null, 
       	     new Object[]{okay, cancel}, 
       	     okay);
	  
	    if (value == 0) {
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
	    } else if (value == 1) {
	    	   new Main();
	    } else System.exit(0);
	    
	    return; 
    }
}
