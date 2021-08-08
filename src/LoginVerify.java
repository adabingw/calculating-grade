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

public class LoginVerify {
	
   protected JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }
	
	public LoginVerify() {
		
		String url = "jdbc:mysql://localhost:3306/grades";
		String username = "root";
		String password = " ";
		
        	JPasswordField jpf = new JPasswordField(10);
 	    	JLabel jl = new JLabel("Password: ");
		jl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
 	    
		JTextField username1 = new JTextField(10);
	    	JLabel label = new JLabel("Username: ");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
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
        
        username1.getDocument().addDocumentListener(new DocumentListener() {
            protected void update() {
                okay.setEnabled(username1.getText().length() > 0 && jpf.getPassword().length > 0);
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
                okay.setEnabled(username1.getText().length() > 0 && jpf.getPassword().length > 0);
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
		
		Object[] message = {
				label, username1,
				jl, jpf,
		};
	    
        int value = JOptionPane.showOptionDialog(
       	     null,
       	     message,
       	     "LOGIN",
       	     JOptionPane.YES_NO_OPTION, 
       	     JOptionPane.QUESTION_MESSAGE, 
       	     null, 
       	     new Object[]{okay, cancel}, 
       	     okay);
	    if (value == 0) {
	       String user = username1.getText();
	       char[] p = jpf.getPassword();
	       String pswrd = String.valueOf(p);
	       System.out.println("user: " + user);
	       System.out.println("password: " + pswrd);
	       
	       try {	
	    	   Connection connection = DriverManager.getConnection(url, username, password);
	    	   System.out.println("connection success!!");
			
	    	   String sql = "SELECT * FROM user_info WHERE user_id = ? && user_password = ?";
	    	   PreparedStatement statement = connection.prepareStatement(sql);
	    	   statement.setString(1, user);
	    	   statement.setString(2, pswrd);
	    	   
	    	   ResultSet rs = statement.executeQuery();
	           while(rs.next()){
	               String name = rs.getString("user_name");
	               System.out.println(name);
	               new userMain(user, name, password);
	           }
			
	    	   ResultSet rows = statement.executeQuery();
	    	   if (!rows.next() ) {
	    		   JLabel l = new JLabel("Username or password incorrect, please try again.");
	    		   l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	    		   l.setHorizontalAlignment(SwingConstants.CENTER);
	    		   JOptionPane.showMessageDialog(null, l, "ERROR", JOptionPane.PLAIN_MESSAGE);
	    	       new LoginVerify();
	    		}  
	    	   
			} catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
	        }	   
	    } else if (value == 1) {
        	JOptionPane.getRootFrame().dispose();   
        } else System.exit(0);
	    
	    return; 
    }
}
