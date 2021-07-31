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
		initialize();
	}

	private void initialize() {
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
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(127)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 194, Short.MAX_VALUE)
					.addGap(115))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addGap(79))
		);
		
		JLabel lblNewLabel = new JLabel(" Grade Calculator");
		lblNewLabel.setFont(new Font("Mindline Slant Demo", Font.PLAIN, 50));
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Calculate Average for All Courses");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton_1.addActionListener(acc);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Calculate Average for One Course");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(occ);
		panel_1.add(btnNewButton);
		
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 450, 300);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public class OneCourseClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
			new averageOneCourse(" ");
		}
	}
	
	public class AllCoursesClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
			new averageAllCourses();
		}
	}
	
}
}
