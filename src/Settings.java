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
	String password = "Will@3229967163";
	
	JFrame frame;
	
	String user_id, name, pswrd;

	changeProfileName cpn = new changeProfileName();
	changeUsername cu = new changeUsername();
	ChangePassword cp = new ChangePassword();
	backButton b = new backButton()
;	
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
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
							.addGap(87))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
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
					.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
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
			JTextField newProfileN = new JTextField(10);
	 	    JLabel j = new JLabel("Change name to: ");
			j.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			
		    JPanel myPanel = new JPanel();
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(j);
		    myPanel.add(newProfileN);
		    
		    int result = JOptionPane.showConfirmDialog(null, myPanel, 
		             "CHANGE PROFILE NAME", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		       String newName = newProfileN.getText();
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
		}
	}}
	
	public class changeUsername implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JTextField newUserN = new JTextField(10);
	 	    JLabel j = new JLabel("Change username to: ");
			j.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			
		    JPanel myPanel = new JPanel();
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(j);
		    myPanel.add(newUserN);
		    
		    int result = JOptionPane.showConfirmDialog(null, myPanel, 
		             "CHANGE USERNAME", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
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
		}
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
		    
		    int result = JOptionPane.showConfirmDialog(null, message, 
		             "CHANGE PASSWORD", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
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
}