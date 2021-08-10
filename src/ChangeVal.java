import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangeVal {
	
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = "root";
	
	protected JOptionPane getOptionPane(JComponent parent) {
	     JOptionPane pane = null;
	     if (!(parent instanceof JOptionPane)) {
	    	 pane = getOptionPane((JComponent)parent.getParent());
	     } else {
	         pane = (JOptionPane) parent;
	     }
	     return pane;
	}
	
	public ChangeVal(int c, Object o, int tableNo, JFrame f, String user_id, String name, String pswrd) {
		
 	    JLabel j = new JLabel("Change to: ");
		j.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	    
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

        final JTextField field = new JTextField(10);
        
        field.getDocument().addDocumentListener(new DocumentListener() {
            protected void update() {
                okay.setEnabled(field.getText().length() > 0);
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
        
	    JPanel myPanel = new JPanel();
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(j);
	    myPanel.add(field);
	    
        int value = JOptionPane.showOptionDialog(
        	     null,
        	     myPanel,
        	     "CHANGE PROFILE NAME",
        	     JOptionPane.YES_NO_OPTION, 
        	     JOptionPane.QUESTION_MESSAGE, 
        	     null, 
        	     new Object[]{okay, cancel}, 
        	     okay);
	    if (value == 0) {
		    String newName = field.getText();
	        try {	
	    	   Connection connection = DriverManager.getConnection(url, username, password);
	    	   System.out.println("connection success!!");
	    	   
               if (tableNo == 1) {
		    	   String sql = "UPDATE course SET course_name = ? WHERE course_name = ?";
		    	   PreparedStatement statement = connection.prepareStatement(sql);
		    	   statement.setString(1, newName);
		    	   statement.setString(2, (String) o);
		    	   
		    	   String sql1 = "UPDATE unit SET course_name = ? WHERE course_name = ?";
		    	   PreparedStatement statement1 = connection.prepareStatement(sql1);
		    	   statement1.setString(1, newName);
		    	   statement1.setString(2, (String) o);
		    	   
		    	   String sql2 = "UPDATE assignments SET course_name = ? WHERE course_name = ?";
		    	   PreparedStatement statement2 = connection.prepareStatement(sql2);
		    	   statement2.setString(1, newName);
		    	   statement2.setString(2, (String) o);
		    	   
					int rows = statement.executeUpdate();
					int rows1 = statement1.executeUpdate();
					int rows2 = statement2.executeUpdate();
	
					if (rows > 0 && rows1 > 0 && rows2 > 0) {
						f.setVisible(false);
						new ViewDatabase(user_id, name, pswrd);
						System.out.println("CHANGED COURSE NAME!");
					}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               } else if (tableNo == 2) {
            	   if (c == 2) {
            		   int w = Integer.parseInt(newName);
            		   int i = Integer.valueOf((String) o);
			    	   String sql = "UPDATE unit SET unit_w = ? WHERE unit_w = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setInt(1, w);
			    	   statement.setInt(2, i);
			    	   
						int rows = statement.executeUpdate();
		
						if (rows > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("CHANGED COURSE NAME!");
						}
            	   } else if (c == 3) {
            		   int w = Integer.parseInt(newName);
            		   int i = Integer.valueOf((String) o);
			    	   String sql = "UPDATE unit SET unit_final = ? WHERE unit_final = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setInt(1, w);
			    	   statement.setInt(2, i);
			    	   
						int rows = statement.executeUpdate();
		
						if (rows > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("CHANGED COURSE NAME!");
						}
            	   } else if (c == 5) {
			    	   String sql = "UPDATE unit SET unit_name = ? WHERE unit_name = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setString(1, newName);
			    	   statement.setString(2, (String) o);
			    	   
			    	   String sql1 = "UPDATE assignments SET unit_name = ? WHERE unit_name = ?";
			    	   PreparedStatement statement1 = connection.prepareStatement(sql1);
			    	   statement1.setString(1, newName);
			    	   statement1.setString(2, (String) o);
			    	   
						int rows = statement.executeUpdate();
						int rows1 = statement1.executeUpdate();
		
						if (rows > 0 && rows1 > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("CHANGED COURSE NAME!");
						}
            	   }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               } else if (tableNo == 3) {
            	   if (c == 3) {
            		   int w = Integer.parseInt(newName);
            		   int i = Integer.valueOf((String) o);
			    	   String sql = "UPDATE assignments SET assign_mark = ? WHERE assign_mark = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setInt(1, w);
			    	   statement.setInt(2, i);
			    	   
						int rows = statement.executeUpdate();
		
						if (rows > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("CHANGED COURSE NAME!");
						}
            	   } else if (c == 6) {
			    	   String sql = "UPDATE assignments SET assign_name = ? WHERE assign_name = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setString(1, newName);
			    	   statement.setString(2, (String) o);
			    	   
						int rows = statement.executeUpdate();
		
						if (rows > 0) {
							f.setVisible(false);
							new ViewDatabase(user_id, name, pswrd);
							System.out.println("CHANGED COURSE NAME!");
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
