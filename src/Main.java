import javax.swing.JOptionPane;

public class Main {
	public static void main (String[] args) {
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
	}
}
