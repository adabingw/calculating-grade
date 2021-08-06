import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClearRow {

	private JFrame frame;
	private JTextField t;
	okClick ok = new okClick();
	JComboBox<String> comboBox, comboBox1;
	
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = "Will@3229967163";
	String user, name, pswrd;
	JFrame f;
	public ClearRow(String user_id, String name, String pswrd, JFrame jf) {
		this.f = jf;
		user = user_id;
		this.name = name;
		this.pswrd = pswrd;
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(26, 42, 381, 147);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel deleteRowsFrom = new JLabel("Delete rows from");
		deleteRowsFrom.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		deleteRowsFrom.setBounds(56, 43, 111, 13);
		panel.add(deleteRowsFrom);
		
		comboBox = new JComboBox<>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(168, 40, 87, 21);
		
		comboBox.addItem("course");
		comboBox.addItem("unit");
		comboBox.addItem("assignments");		

		panel.add(comboBox);
		
		JLabel where = new JLabel("where");
		where.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		where.setBounds(265, 44, 45, 13);
		panel.add(where);
		
		comboBox1 = new JComboBox<>();
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setBounds(56, 70, 153, 21);
		panel.add(comboBox1);
		
		comboBox1.addItem("COURSE ID");
		comboBox1.addItem("COURSE NAME");
		comboBox1.addItem("COURSE MARK");
		comboBox1.addItem("UNIT ID");
		comboBox1.addItem("UNIT FINAL");
		comboBox1.addItem("UNIT NAME");
		comboBox1.addItem("ASSIGNMENT ID");
		comboBox1.addItem("ASSIGNMENT MARK");
		comboBox1.addItem("ASSIGNMENT NAME");

		
		JLabel equals = new JLabel("=");
		equals.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		equals.setBounds(219, 73, 9, 13);
		panel.add(equals);
		
		t = new JTextField();
		t.setBounds(234, 71, 96, 19);
		panel.add(t);
		t.setColumns(10);
		
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
		panel.add(cancelButton);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public class okClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			f.setVisible(false);
			int d1;
			String table = comboBox.getSelectedItem().toString();
			System.out.println(table);
			String attribute = comboBox1.getSelectedItem().toString();
			System.out.println(attribute);
		    String data = t.getText();
			System.out.println(data);
			IsNumber in = new IsNumber();
	        d1 = in.stringToInt(data);
			     
				try {
										
					Connection connection = DriverManager.getConnection(url, username, password);
					System.out.println("connection success!!");
															
					String sql = " ";
					if (table.equals("course")) {
						if ("COURSE ID".equals(attribute)) {
							sql = "DELETE FROM course WHERE course_id = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("COURSE - COURSE_ID UPDATE SUCCESSFUL");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") || !attribute.equals("COURSE MARK") ||!attribute.equals("COURSE NAME")) {
								JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
					    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
								else {JOptionPane.showMessageDialog(null, "Cannot find data!",
					    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("COURSE NAME")) {
							sql = "DELETE FROM course WHERE course_name = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setString(1, data);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("COURSE - COURSE_NAME UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") || !attribute.equals("COURSE MARK") ||!attribute.equals("COURSE NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("COURSE MARK")) {
							sql = "DELETE FROM course WHERE course_mark = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("COURSE - COURSE_MARK UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") || !attribute.equals("COURSE MARK") ||!attribute.equals("COURSE NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else { JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					} else if (table.equals("unit")) {
						if (attribute.equals("COURSE ID")) {
							sql = "DELETE FROM unit WHERE course_id = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("UNIT - COURSE_ID UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("UNIT FINAL") || !attribute.equals("UNIT ID")|| !attribute.equals("UNIT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("COURSE NAME")) {
							sql = "DELETE FROM unit WHERE course_name = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setString(1, data);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("UNIT - COURSE_NAME UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("UNIT FINAL") || !attribute.equals("UNIT ID")|| !attribute.equals("UNIT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("UNIT ID")) {
							sql = "DELETE FROM unit WHERE unit_id = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								frame.setVisible(false);
								new ViewDatabase(user, name, pswrd);
								System.out.println("UNIT - UNIT_ID UPDATE SUCCESSFUL!");
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("UNIT FINAL") || !attribute.equals("UNIT ID")|| !attribute.equals("UNIT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("UNIT MARK")) {
							sql = "DELETE FROM unit WHERE unit_mark = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("UNIT - UNIT_MARK UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("UNIT FINAL") || !attribute.equals("UNIT ID")|| !attribute.equals("UNIT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("UNIT FINAL")) {
							sql = "DELETE FROM unit WHERE unit_final = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("UNIT - UNIT_FINAL UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("UNIT FINAL") || !attribute.equals("UNIT ID")|| !attribute.equals("UNIT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("UNIT NAME")) {
							sql = "DELETE FROM unit WHERE unit_name = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("UNIT - UNIT_NAME UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("UNIT FINAL") || !attribute.equals("UNIT ID")|| !attribute.equals("UNIT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else { JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
					} else if (table.equals("assignments")) {
						if (attribute.equals("COURSE ID")) {
							sql = "DELETE FROM assignments WHERE course_id = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("ASSIGN - COURSE ID UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("ASSIGNMENT ID") || !attribute.equals("UNIT ID")|| !attribute.equals("ASSIGNMENT NAME") || !attribute.equals("ASSIGNMENT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("COURSE NAME")) {
							sql = "DELETE FROM assignment WHERE course_name = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setString(1, data);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("ASSIGN - COURSE NAME UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("ASSIGNMENT ID") || !attribute.equals("UNIT ID")|| !attribute.equals("ASSIGNMENT NAME") || !attribute.equals("ASSIGNMENT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("UNIT ID")) {
							sql = "DELETE FROM assignments WHERE unit_id = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("ASSIGN - UNIT ID UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("ASSIGNMENT ID") || !attribute.equals("UNIT ID")|| !attribute.equals("ASSIGNMENT NAME") || !attribute.equals("ASSIGNMENT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("ASSIGNMENT MARK")) {
							sql = "DELETE FROM assignments WHERE assign_mark = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("ASSIGN - ASSIGN_MARK UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("ASSIGNMENT ID") || !attribute.equals("UNIT ID")|| !attribute.equals("ASSIGNMENT NAME") || !attribute.equals("ASSIGNMENT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("ASSIGNMENT ID")) {
							sql = "DELETE FROM assignments WHERE assign_id = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								frame.setVisible(false);
								new ViewDatabase(user, name, pswrd);
								System.out.println("ASSIGN - ASSIGNMENT ID UPDATE SUCCESSFUL!");
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("ASSIGNMENT ID") || !attribute.equals("UNIT ID")|| !attribute.equals("ASSIGNMENT NAME") || !attribute.equals("ASSIGNMENT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("ASSIGNMENT NAME")) {
							sql = "DELETE FROM assignments WHERE assign_name = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("ASSIGN - ASSIGN NAME UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("ASSIGNMENT ID") || !attribute.equals("UNIT ID")|| !attribute.equals("ASSIGNMENT NAME") || !attribute.equals("ASSIGNMENT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else if (attribute.equals("UNIT NAME")) {
							sql = "DELETE FROM assignments WHERE unit_name = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setInt(1, d1);
							
							int rows = statement.executeUpdate();
							if (rows > 0) {
								new ViewDatabase(user, name, pswrd);
								System.out.println("ASSIGN - UNIT NAME UPDATE SUCCESSFUL!");
								frame.setVisible(false);
							} else if (rows == 0) {
								if (!attribute.equals("COURSE ID") ||!attribute.equals("ASSIGNMENT ID") || !attribute.equals("UNIT ID")|| !attribute.equals("ASSIGNMENT NAME") || !attribute.equals("ASSIGNMENT MARK") ||!attribute.equals("COURSE NAME") ||!attribute.equals("UNIT NAME")) {
									JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE); }
									else {JOptionPane.showMessageDialog(null, "Cannot find data!",
						    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);}
							}
							
							statement.close();
							connection.close();
							
						} else { JOptionPane.showMessageDialog(null, "COLUMN NAME NOT FOUND IN TABLE",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
										
				} catch (SQLException e) {
						System.out.println("& i oop");
						e.printStackTrace();
				}
			     
			
		}}
	
}
