import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class averageAllCourses {
	public averageAllCourses() {
		qForCourses C = new qForCourses();
		int NCourses = C.AskCourses(); 
		System.out.println("Number of Courses: " + NCourses);
		String[] CNames = new String[NCourses];
		int[] avg = new int[NCourses];
		for (int i = 0; i < NCourses; i++) {
			CNames[i] = C.CourseName(i + 1);
			System.out.println("Course name: " + CNames[i]);
			averageOneCourse AOC = new averageOneCourse(CNames[i]);
			avg[i] = AOC.returnAverage();
			System.out.println("The course mark for " + CNames[i] + " is " + avg[i]);
		}
		int sumAverage = IntStream.of(avg).sum();
		System.out.println("Total average is: " + sumAverage);
		JOptionPane.showMessageDialog(null, "The final mark for this course is: " + sumAverage);
	}
}
