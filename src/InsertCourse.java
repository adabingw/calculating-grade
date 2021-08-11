

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.JButton;

public class InsertCourse {

	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = "root";
	int id;
	
	protected JOptionPane getOptionPane(JComponent parent) {
	     JOptionPane pane = null;
	     if (!(parent instanceof JOptionPane)) {
	    	 pane = getOptionPane((JComponent)parent.getParent());
	     } else {
	         pane = (JOptionPane) parent;
	     }
	     return pane;
	}
	
	public InsertCourse(String user_id, String name, String pswrd, JFrame f) {
 	    JLabel j = new JLabel("Enter new course name: ");
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
        	     "CHANGE VALUE",
        	     JOptionPane.YES_NO_OPTION, 
        	     JOptionPane.QUESTION_MESSAGE, 
        	     null, 
        	     new Object[]{okay, cancel}, 
        	     okay);
	    if (value == 0) {
		    String newCourse = field.getText();
			try {
	           Connection connection = DriverManager.getConnection(url, username, password);
		 	   String sql = "SELECT MAX(course_id) as MAXID FROM course";
		 	   PreparedStatement statement = connection.prepareStatement(sql);
		 	   
		 	   ResultSet rs = statement.executeQuery();
		 	   
		 	   while(rs.next()) {
		 		   id = rs.getInt("MAXID");
		 		   System.out.println(id);
		 	   }
		 	   
		 	   String sql1 = "INSERT INTO course (course_id, course_name, user_id) VALUES (?, ?, ?)";
		 	   PreparedStatement statement1 = connection.prepareStatement(sql1);
		 	   statement1.setInt(1, id+1);
		 	   statement1.setString(2, newCourse);
		 	   statement1.setString(3, user_id);
		 	   
		 	   int rows = statement1.executeUpdate();
		 	   
		 	   if (rows > 0) {
		 		   f.setVisible(false);
		 		   new ViewDatabase(user_id, name, pswrd);
		 	   }
		
			} catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
			}	
	    }	

	}
}
