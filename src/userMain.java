import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
//import javax.swing.JOptionPane;

public class userMain {	
	
	JFrame frame;

	OneCourseClick occ = new OneCourseClick();
	AllCoursesClick acc = new AllCoursesClick();
	logoutClick lc = new logoutClick();
	settingsClick sc = new settingsClick();
	String user_id;
	String name;
	String password;

	public userMain(String username, String name, String password) {
		user_id = username;
		this.name = name;
		this.password = password;
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(120)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 194, Short.MAX_VALUE))
					.addGap(122))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		JButton settingsButton = new JButton("SETTINGS");
		settingsButton.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		settingsButton.setBackground(Color.WHITE);
		settingsButton.addActionListener(sc);
		panel_1_1.add(settingsButton);
		
		JLabel label = new JLabel("Hello, " + name + "!");
		label.setFont(new Font("Mindline Slant Demo", Font.PLAIN, 50));
		panel.add(label);
		
		JButton avgAll = new JButton("Calculate Average for All Courses");
		avgAll.setBackground(Color.WHITE);
		avgAll.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		avgAll.addActionListener(acc);
		panel_1.add(avgAll);
		
		JButton avgOne = new JButton("Calculate Average for One Course");
		avgOne.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		avgOne.setBackground(Color.WHITE);
		avgOne.addActionListener(occ);
		panel_1.add(avgOne);
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JButton checkGrades = new JButton("Check grades");
		checkGrades.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		checkGrades.addActionListener(lc);
		checkGrades.setBackground(Color.WHITE);
		panel_1.add(checkGrades);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JButton logoutButton = new JButton("LOGOUT");
		logoutButton.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		logoutButton.addActionListener(lc);
		logoutButton.setBackground(Color.WHITE);
		panel_1_1.add(logoutButton);
		
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 450, 300);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public class OneCourseClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				System.out.println("OCC CLICKED!");
				new averageOneCourse(" ", false, user_id, name, true, password);
		}
	}
	
	public class AllCoursesClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				System.out.println("ACC CLICKED!");
				new averageAllCourses(false, user_id, name, password);
		}
	}
	
	public class logoutClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.dispose();
			System.out.println("LOGOUT CLICKED!");
			
		}
	}
	
	public class settingsClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.dispose();
			System.out.println("SETTINGS CLICKED!");
			new Settings(user_id, name, password);			
		}
	}
}
