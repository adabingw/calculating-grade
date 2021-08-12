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

public class InsertAssignment2 {

	JFrame frame, f;
	JComboBox<String> comboBox, comboBox1;
	String user, name, pswrd;
	
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = "root";
	int id, aID, cID;
	String[] unitNames;
	String courseName;
	okClick ok = new okClick();
	backB back = new backB();
	cancelB c = new cancelB();
	
	protected JOptionPane getOptionPane(JComponent parent) {
	     JOptionPane pane = null;
	     if (!(parent instanceof JOptionPane)) {
	    	 pane = getOptionPane((JComponent)parent.getParent());
	     } else {
	         pane = (JOptionPane) parent;
	     }
	     return pane;
	}
	
	public InsertAssignment2(String user, String name, String pswrd, JFrame f, String courseName) {
		this.courseName = courseName;
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
		comboBox.setBounds(122, 39, 117, 21);

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
		
		JButton btnBack = new JButton("BACK");
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(134, 116, 78, 21);
		btnBack.addActionListener(back);
		panel.add(btnBack);
		
		try {
	           Connection connection = DriverManager.getConnection(url, username, password);
		 	   String sql = "SELECT MAX(unit_id) as MAXID FROM unit WHERE course_name = ?";
		 	   PreparedStatement statement = connection.prepareStatement(sql);
		 	   statement.setString(1, courseName);
		 	   
		 	   ResultSet rs = statement.executeQuery();
		 	   
		 	   while(rs.next()) {
		 		   id = rs.getInt("MAXID");
		 		   System.out.println(id);
		 		   
			 	   for (int i = 100; i < id +1 ; i ++) {
				 	   String sql2 = "SELECT unit_name FROM unit WHERE course_name = ? && unit_id = ?";
				 	   PreparedStatement statement2 = connection.prepareStatement(sql2);
				 	   statement2.setString(1, courseName);
				 	   statement2.setInt(2, i);
				 	   
				 	   String sql4 = "SELECT course_id FROM course WHERE course_name = ?";
				 	   PreparedStatement statement4 = connection.prepareStatement(sql4);
				 	   statement4.setString(1, courseName);
				 	   
				 	   ResultSet rs2 = statement2.executeQuery();
				 	   ResultSet rs4 = statement4.executeQuery();
				 	   
				 	   while(rs2.next() && rs4.next()) {
				 		   unitNames = new String[id - 99];
				 		   unitNames[i - 100] = rs2.getString("unit_name");
				 		   cID = rs4.getInt("course_id");
					 	   System.out.println(courseName);
					 	   comboBox.addItem(unitNames[i - 100]);
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
			String unitPick = comboBox.getSelectedItem().toString();
			
		    	JLabel j = new JLabel("Enter new assignment name: ");
			j.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			
			JLabel j1 = new JLabel("Enter new assignment mark: ");
			j1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		    
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

			final JTextField field2 = new JTextField(10);

			field.getDocument().addDocumentListener(new DocumentListener() {
			    protected void update() {
				okay.setEnabled(field.getText().length() > 0 && field2.getText().length() > 0);
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
				okay.setEnabled(field.getText().length() > 0 && field2.getText().length() > 0);
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
					j1, field2,
			};

			int value = JOptionPane.showOptionDialog(
				     null,
				     message,
				     "INSERT ASSIGNMENTS",
				     JOptionPane.YES_NO_OPTION, 
				     JOptionPane.QUESTION_MESSAGE, 
				     null, 
				     new Object[]{okay, cancel}, 
				     okay);
		        if (value == 0) {
			    String newAssignName = field.getText();
			    String newAssignMark = field2.getText();
			    int newAMark = Integer.parseInt(newAssignMark);
				try {
			           Connection connection = DriverManager.getConnection(url, username, password);
			           String sql1 = "SELECT MAX(assign_id) as MAXID FROM assignments WHERE course_name = ?";
				 	   PreparedStatement statement1 = connection.prepareStatement(sql1);
				 	   statement1.setString(1, courseName);
				 	   
				 	   ResultSet rs1 = statement1.executeQuery();
				 	   
				 	   while(rs1.next()) {
				 		   aID = rs1.getInt("MAXID");
				 		   System.out.println(aID);
				 		   
					 	   String sql3 = "INSERT INTO assignments (course_id, course_name, user_id, unit_id, unit_name, assign_name, assign_mark, assign_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					 	   PreparedStatement statement3 = connection.prepareStatement(sql3);
					 	   statement3.setInt(1, cID);
					 	   statement3.setString(2, courseName);
					 	   statement3.setString(3, user);
					 	   statement3.setInt(4, id);
					 	   statement3.setString(5, unitPick);
					 	   statement3.setString(6, newAssignName);
					 	   statement3.setInt(7, newAMark);
					 	   statement3.setInt(8, aID + 1);

					 	   
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
	}}}
	
	public class backB implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
			new InsertAssignment(user, name, pswrd, f);
			System.out.println("BACK CLICKED!");
		}
	}
	
	public class cancelB implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
		}
	}
}
