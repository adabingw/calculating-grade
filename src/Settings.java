import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
//import javax.swing.JOptionPane;

public class Settings {
	
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = " ";
	
	JFrame frame;
	
	String user_id, name, pswrd;

	changeProfileName cpn = new changeProfileName();
	changeUsername cu = new changeUsername();
	ChangePassword cp = new ChangePassword();
	backButton b = new backButton();
	deleteAcc da = new deleteAcc();
	
	public Settings(String user_id, String name, String pswrd) {
		this.user_id = user_id;
		this.name = name;
		this.pswrd = pswrd;
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		
		JButton backButton = new JButton("BACK");
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		backButton.addActionListener(b);
		backButton.setBackground(Color.WHITE);
		
		JButton deleteAccButton = new JButton("DELETE ACCOUNT");
		deleteAccButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		deleteAccButton.addActionListener(da);
		deleteAccButton.setBackground(Color.WHITE);

		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
							.addGap(87))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(deleteAccButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(71))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteAccButton))
					.addGap(44))
		);
		
		JButton changePN = new JButton("CHANGE");
		changePN.addActionListener(cpn);
		changePN.setBackground(Color.WHITE);
		changePN.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_1_1.add(changePN);
		
		JButton changeU = new JButton("CHANGE");
		changeU.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		changeU.addActionListener(cu);
		changeU.setBackground(Color.WHITE);
		panel_1_1.add(changeU);
		
		JButton changePSWRD = new JButton("CHANGE");
		changePSWRD.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		changePSWRD.addActionListener(cp);
		changePSWRD.setBackground(Color.WHITE);
		panel_1_1.add(changePSWRD);
		panel_1.setLayout(null);
		
		JLabel profileLabel = new JLabel("PROFILE NAME:");
		profileLabel.setBounds(10, 5, 111, 18);
		profileLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(profileLabel);
		
		JLabel nameLabel = new JLabel(name);
		nameLabel.setBounds(131, 5, 97, 18);
		nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(nameLabel);
		
		JLabel usernameLabel = new JLabel("USERNAME:");
		usernameLabel.setBounds(10, 33, 111, 18);
		usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(usernameLabel);
		
		JLabel userLabel = new JLabel(user_id);
		userLabel.setBounds(131, 33, 97, 18);
		userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(userLabel);
		
		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		passwordLabel.setBounds(10, 65, 111, 18);
		panel_1.add(passwordLabel);
		
		JLabel hashedPassword = new JLabel("**********");
		hashedPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		hashedPassword.setBounds(131, 65, 97, 18);
		panel_1.add(hashedPassword);
		
		
		JLabel lblProfile = new JLabel("Profile");
		lblProfile.setHorizontalAlignment(SwingConstants.LEFT);
		lblProfile.setVerticalAlignment(SwingConstants.TOP);
		lblProfile.setFont(new Font("Mindline Slant Demo", Font.PLAIN, 50));
		panel.add(lblProfile);
		
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 450, 300);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public class changeProfileName implements ActionListener {
		public void actionPerformed(ActionEvent event) {
	 	    JLabel j = new JLabel("Change name to: ");
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
		       System.out.println("New name: " + newName);
			       try {	
			    	   Connection connection = DriverManager.getConnection(url, username, password);
			    	   System.out.println("connection success!!");
					
			    	   String sql = "UPDATE user_info SET user_name = ? WHERE user_id = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setString(1, newName);
			    	   statement.setString(2, user_id);
			    	   
						int rows2 = statement.executeUpdate();
						if (rows2 > 0) {
							System.out.println("UPDATE PROFILE NAME!");
						}
					} catch (SQLException e) {
						System.out.println("& i oop");
						e.printStackTrace();
			        }
		       frame.setVisible(false);
		       new userMain(user_id, newName, pswrd);
		   } else if (value == 1) {
	        	JOptionPane.getRootFrame().dispose();   
	        } else System.exit(0);
	}}
	
	public class changeUsername implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JTextField newUserN = new JTextField(10);
	 	    JLabel j = new JLabel("Change username to: ");
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
	        	     "CHANGE USERNAME",
	        	     JOptionPane.YES_NO_OPTION, 
	        	     JOptionPane.QUESTION_MESSAGE, 
	        	     null, 
	        	     new Object[]{okay, cancel}, 
	        	     okay);
		    if (value == 0) {
		       String newUser = newUserN.getText();
		       System.out.println("New name: " + newUser);
			       try {	
			    	   Connection connection = DriverManager.getConnection(url, username, password);
			    	   System.out.println("connection success!!");
			    	   
			    	   String sql = "SELECT * FROM user_info WHERE user_id = ?";
			    	   PreparedStatement statement = connection.prepareStatement(sql);
			    	   statement.setString(1, newUser);
			    	   
			    	   ResultSet rs = statement.executeQuery();
			           if (!rs.next()){
				    	   String sql1 = "UPDATE user_info SET user_id = ? WHERE user_password = ?";
				    	   PreparedStatement statement1 = connection.prepareStatement(sql1);
				    	   statement1.setString(1, newUser);
				    	   statement1.setString(2, pswrd);
				    	   
							int rows2 = statement1.executeUpdate();
							if (rows2 > 0) {
								System.out.println("UPDATE USERNAME!");
							}
			           } else {
			        	   JLabel l = new JLabel("Username has already been taken.");
			    		   l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			    		   l.setHorizontalAlignment(SwingConstants.CENTER);
			    		   JOptionPane.showMessageDialog(null, l, "ERROR", JOptionPane.PLAIN_MESSAGE);
			           }
					} catch (SQLException e) {
						System.out.println("& i oop");
						e.printStackTrace();
			        }
		       frame.setVisible(false);
		       new userMain(newUser, name, pswrd);
		   } else if (value == 1) {
	        	JOptionPane.getRootFrame().dispose();   
	        } else System.exit(0);
		}
	}
	
	public class ChangePassword implements ActionListener {
		public void actionPerformed(ActionEvent event) {
	        JPasswordField oldPswrd = new JPasswordField();
	        JPasswordField newPswrd = new JPasswordField();
	        JLabel j = new JLabel("Type in old password: ");
	 	    JLabel j2 = new JLabel("Type in new password: ");
			j.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			j2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			
			Object[] message = {
				    j, oldPswrd,
				    j2, newPswrd,
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
	        
	        oldPswrd.getDocument().addDocumentListener(new DocumentListener() {
	            protected void update() {
	                okay.setEnabled(oldPswrd.getPassword().length > 0 && newPswrd.getPassword().length > 0);
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
	        
	        newPswrd.getDocument().addDocumentListener(new DocumentListener() {
	            protected void update() {
	                okay.setEnabled(oldPswrd.getPassword().length > 0 && newPswrd.getPassword().length > 0);
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
	        	     "CHANGE USERNAME",
	        	     JOptionPane.YES_NO_OPTION, 
	        	     JOptionPane.QUESTION_MESSAGE, 
	        	     null, 
	        	     new Object[]{okay, cancel}, 
	        	     okay);
		    if (value == 0) {
			   char[] oldp = oldPswrd.getPassword();
			   String oldP = String.valueOf(oldp);
		       char[] p = newPswrd.getPassword();
		       String newP = String.valueOf(p);
		       System.out.println("New name: " + newP);
		       try {	
		    	   Connection connection = DriverManager.getConnection(url, username, password);
		    	   System.out.println("connection success!!");
		    	   
		    	   String sql = "SELECT * FROM user_info WHERE user_id = ? && user_password = ?";
		    	   PreparedStatement statement = connection.prepareStatement(sql);
		    	   statement.setString(1, user_id);
		    	   statement.setString(2, oldP);
		    	   
		    	   ResultSet rs = statement.executeQuery();
		           if (rs.next()){
		               String oldPassword = rs.getString("user_password");
		               if (oldP.equals(oldPassword)) {
		            	   String sql1 = "UPDATE user_info SET user_password = ? WHERE user_id = ?";
				    	   PreparedStatement statement1 = connection.prepareStatement(sql1);
				    	   statement1.setString(1, newP);
				    	   statement1.setString(2, user_id);
				    	   
							int rows2 = statement1.executeUpdate();
							if (rows2 > 0) {
								System.out.println("UPDATE PASSWORD!");
							}
		               }
		           } else {
		        	   JLabel l = new JLabel("Old password does not match");
		    		   l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		    		   l.setHorizontalAlignment(SwingConstants.CENTER);
		    		   JOptionPane.showMessageDialog(null, l, "ERROR", JOptionPane.PLAIN_MESSAGE);
		           }
				} catch (SQLException e) {
					System.out.println("& i oop");
					e.printStackTrace();
		        }
	       frame.setVisible(false);
	       new userMain(user_id, name, newP);
		}
			
		}
	}
	
	public class backButton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
			new userMain(user_id, name, pswrd);
			System.out.println("BACK CLICKED!");
		}
	}
	
	public class deleteAcc implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JLabel l = new JLabel("This action cannot be reversed. Continue?");
			l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			l.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel l1 = new JLabel("Error occured");
			l1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			
			if (JOptionPane.showConfirmDialog(null, l, "WARNING",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
		            try {	
						Connection connection = DriverManager.getConnection(url, username, password);
						System.out.println("connection success!!");
																		
						String sql = "DELETE FROM user_info WHERE user_id = ?";
						PreparedStatement statement = connection.prepareStatement(sql);
						statement.setString(1, user_id);
										
						int rows = statement.executeUpdate();
						if (rows > 0) {
							Main window = new Main();
							window.frame.setVisible(true);
							frame.setVisible(false);
							System.out.println("Account deleted!");
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
		}
	}
}
