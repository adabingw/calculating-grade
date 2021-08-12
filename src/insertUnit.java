import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class insertUnit {

	JFrame frame, f;
	JComboBox<String> comboBox, comboBox1;
	String user, name, pswrd;
	
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = " ";
	int id, u_id;
	String courseName;
	String[] unitNames;
	okClick ok = new okClick();
	cancelB cancel = new cancelB();
	
	protected JOptionPane getOptionPane(JComponent parent) {
	     JOptionPane pane = null;
	     if (!(parent instanceof JOptionPane)) {
	    	 pane = getOptionPane((JComponent)parent.getParent());
	     } else {
	         pane = (JOptionPane) parent;
	     }
	     return pane;
	}
	
	public insertUnit(String user, String name, String pswrd, JFrame f) {
		this.user = user;
		this.name = name;
		this.pswrd = pswrd;
		this.f = f;
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(26, 42, 381, 147);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel deleteRowsFrom = new JLabel("Insert into");
		deleteRowsFrom.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		deleteRowsFrom.setBounds(56, 43, 63, 13);
		panel.add(deleteRowsFrom);
		
		comboBox = new JComboBox<>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBox.setBounds(122, 39, 121, 21);

		panel.add(comboBox);
		
		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		okButton.setForeground(new Color(0, 0, 0));
		okButton.setBackground(Color.WHITE);
		okButton.addActionListener(ok);
		okButton.setBounds(219, 116, 63, 21);
		panel.add(okButton);
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cancelButton.setBounds(286, 116, 85, 21);
		cancelButton.addActionListener(cancel);
		panel.add(cancelButton);
		
		try {
	           Connection connection = DriverManager.getConnection(url, username, password);
		 	   String sql = "SELECT MAX(course_id) as MAXID FROM course";
		 	   PreparedStatement statement = connection.prepareStatement(sql);
		 	   
		 	   ResultSet rs = statement.executeQuery();
		 	   
		 	   while(rs.next()) {
		 		   id = rs.getInt("MAXID");
		 		   System.out.println(id);
		 		   
			 	   for (int i = 1; i < id+1; i ++) {
				 	   String sql1 = "SELECT course_name FROM course WHERE course_id = ?";
				 	   PreparedStatement statement1 = connection.prepareStatement(sql1);
				 	   statement1.setInt(1, i);
				 	   
				 	   ResultSet rs1 = statement1.executeQuery();
				 	   
				 	   while(rs1.next()) {
				 		   courseName = rs1.getString("course_name");
					 	   System.out.println(courseName);
					 	   comboBox.addItem(courseName);
				 	   }
			 	   }
		 	   }
		
			} catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
			}
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public class okClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
			String coursePick = comboBox.getSelectedItem().toString();
			System.out.println(coursePick);
			
		    JLabel j = new JLabel("Enter new unit name: ");
			j.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			
		    JLabel j1 = new JLabel("Enter unit weight: ");
			j1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			
		    JLabel j2 = new JLabel("Enter unit final: ");
			j2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		    
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
	        final JTextField field1 = new JTextField(10);
	        final JTextField field2 = new JTextField(10);
	        	        
	        field.getDocument().addDocumentListener(new DocumentListener() {
	            protected void update() {
	                okay.setEnabled(field.getText().length() > 0 && field1.getText().length() > 0 && field2.getText().length() > 0);
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
	        
	        field1.getDocument().addDocumentListener(new DocumentListener() {
	            protected void update() {
	                okay.setEnabled(field.getText().length() > 0 && field1.getText().length() > 0 && field2.getText().length() > 0);
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
	        
	        field2.getDocument().addDocumentListener(new DocumentListener() {
	            protected void update() {
	                okay.setEnabled(field.getText().length() > 0 && field1.getText().length() > 0 && field2.getText().length() > 0);
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
	        		j, field,
	        		j1, field1,
	        		j2, field2,
	        };
		    
	        int value = JOptionPane.showOptionDialog(
	        	     null,
	        	     message,
	        	     "INSERT UNIT",
	        	     JOptionPane.YES_NO_OPTION, 
	        	     JOptionPane.QUESTION_MESSAGE, 
	        	     null, 
	        	     new Object[]{okay, cancel}, 
	        	     okay);
		    if (value == 0) {
			    String newUnitName = field.getText();
			    String newUnitW = field1.getText();
			    String newUnitFinal = field2.getText();
			    int uF = Integer.parseInt(newUnitFinal);
			    int uW = Integer.parseInt(newUnitW);
				try {
			           Connection connection = DriverManager.getConnection(url, username, password);
			           String sql1 = "SELECT MAX(unit_id) as MAXID1 FROM unit WHERE course_name = ?";
				 	   PreparedStatement statement1 = connection.prepareStatement(sql1);
				 	   statement1.setString(1, coursePick);
				 	   
				 	   String sql4 = "SELECT course_id FROM course WHERE course_name = ?";
				 	   PreparedStatement statement4 = connection.prepareStatement(sql4);
				 	   statement4.setString(1, coursePick);
				 	   
				 	   ResultSet rs1 = statement1.executeQuery();
				 	   ResultSet rs4 = statement4.executeQuery();
				 	   
				 	   while(rs1.next() && rs4.next()) {
				 		   id = rs4.getInt("course_id");
				 		   u_id = rs1.getInt("MAXID1");
				 		   System.out.println("MAX unit id is " + u_id);
				 		   
					 	   String sql3 = "INSERT INTO unit (course_id, course_name, user_id, unit_id, unit_name, unit_final, unit_w) VALUES (?, ?, ?, ?, ?, ?, ?)";
					 	   PreparedStatement statement3 = connection.prepareStatement(sql3);
					 	   statement3.setInt(1, id);
					 	   statement3.setString(2, coursePick);
					 	   statement3.setString(3, user);
					 	   statement3.setInt(4, u_id+1);
					 	   statement3.setString(5, newUnitName);
					 	   statement3.setInt(6, uF);
					 	   statement3.setInt(7, uW);
					 	   
					 	   int rows = statement3.executeUpdate();
					 	   
					 	   if (rows > 0) {
					 		   f.setVisible(false);
					 		   new ViewDatabase(user, name, pswrd);
					 	   }
				 	   }
				
					} catch (SQLException e) {
						System.out.println("& i oop");
						e.printStackTrace();
					}
		    } else {
				JOptionPane.getRootFrame().dispose();   
		    }
	}}
	
	public class cancelB implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
		}
	}
}
