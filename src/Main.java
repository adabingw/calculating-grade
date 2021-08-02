// import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.UIManager;

public class Main {
/*	public static void main (String[] args) {
		Object[] options = {"Average of one course",
                "Average of all courses",
                };
		int n = JOptionPane.showOptionDialog(null, "What average would you like to calculate?", "Welcome!",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		if (n == JOptionPane.YES_OPTION) {
			System.out.println("Selected 'Average for one course'.");
			new averageOneCourse(null);
		} else if (n == JOptionPane.NO_OPTION) {
			System.out.println("Selected 'Average of all courses'.");
			new averageAllCourses();
		} else System.exit(0);
	} */
	
	JFrame frame;

	OneCourseClick occ = new OneCourseClick();
	AllCoursesClick acc = new AllCoursesClick();
	loginClick lc = new loginClick();
	signUpClick suc = new signUpClick();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					UIManager.put("OptionPane.background", Color.WHITE);
					UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
					UIManager.put("Button.background", Color.white);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
			frame = new JFrame();
			frame.getContentPane().setBackground(Color.WHITE);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(59)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 318, Short.MAX_VALUE)
						.addGap(59))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(127)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 194, Short.MAX_VALUE)
						.addGap(115))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(60, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addGap(58))
			);
			
			JLabel label = new JLabel(" Grade Calculator");
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
			
			JButton btnLogin = new JButton("LOGIN");
			btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnLogin.addActionListener(lc);
			btnLogin.setBackground(Color.WHITE);
			panel_1.add(btnLogin);
			
			JButton btnSignUp = new JButton("SIGN UP");
			btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnSignUp.addActionListener(suc);
			btnSignUp.setBackground(Color.WHITE);
			panel_1.add(btnSignUp);
			
			frame.getContentPane().setLayout(groupLayout);
			frame.setBounds(100, 100, 450, 300);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public class OneCourseClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JLabel label = new JLabel("Your progress will not be saved. Continue?");
			label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			if (JOptionPane.showConfirmDialog(null, label, null,
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				frame.setVisible(false);
				new averageOneCourse(" ", true, null, null, true, null);
			} else {
				JOptionPane.getRootFrame().dispose();   
			}
		}
	}
	
	public class AllCoursesClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JLabel label = new JLabel("Your progress will not be saved. Continue?");
			label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			if (JOptionPane.showConfirmDialog(null, label, null,
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				frame.setVisible(false);
				new averageAllCourses(true, null, null, null);
			} else {
				JOptionPane.getRootFrame().dispose();   
			}
		}
	}
	
	public class loginClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
			new LoginVerify();
			System.out.println("LOGIN CLICKED!");
		}
	}
	
	public class signUpClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
			System.out.println("SIGNUP CLICKED!");
		}
	}
	
}
