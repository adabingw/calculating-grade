import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class AverageAllCourses {
	public AverageAllCourses(boolean guest, String user_id, String name, String pswrd) {
		
		String url = "jdbc:mysql://localhost:3306/grades";
		String username = "root";
		String password = "root";
		
		QForCourses c = new QForCourses();
		int nCourses = c.askCourses();
		System.out.println("Number of Courses: " + nCourses);
		String[] cNames = new String[nCourses];
		int[] avg = new int[nCourses];
		
		for (int i = 0; i < nCourses; i++) {
			cNames[i] = c.courseName(i + 1);
			System.out.println("Course name: " + cNames[i]);
			AverageOneCourse aoc = new AverageOneCourse(cNames[i], guest, user_id, name, false, pswrd);
			avg[i] = aoc.returnAverage();
			System.out.println("The course mark for " + cNames[i] + " is " + avg[i]);
			
			if (guest == false) {
			
				try {				
					Connection connection = DriverManager.getConnection(url, username, password);
					System.out.println("connection success!!");
					
					String sql = "UPDATE unit SET course_id = ? WHERE course_name = ?";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setInt(1, i+1);
					statement.setString(2, cNames[i]);
					//
					String sql1 = "UPDATE assignments SET course_id = ? WHERE course_name = ?";
					PreparedStatement statement1 = connection.prepareStatement(sql1);
					statement1.setInt(1, i+1);
					statement1.setString(2, cNames[i]);
							
					int rows = statement.executeUpdate();
					if (rows > 0) {
						System.out.println("COURSE_ID UNIT UPDATE SUCCESSFUL!");
					}
					
					int rows1 = statement1.executeUpdate();
					if (rows1 > 0) {
						System.out.println("COURSE_ID ASSIGNMENTS UPDATE SUCCESSFUL!");
					} 
					
					String sql2 = "INSERT INTO course (COURSE_NAME, COURSE_MARK, USER_ID, COURSE_ID) VALUES (?, ?, ?, ?)";
					PreparedStatement statement2 = connection.prepareStatement(sql2);
					statement2.setString(1, cNames[i]);
					statement2.setInt(2, avg[i]);
					statement2.setString(3, user_id);
					statement2.setInt(4, i+1);
					
					int rows2 = statement2.executeUpdate();
					if (rows2 > 0) {
						System.out.println("COURSE TABLE update successful!!");
					}
				
					
					statement.close();
					connection.close();
				
					statement1.close();
					connection.close();
					
					statement2.close();
					connection.close();
				} catch (SQLException e) {
					System.out.println("& i oop");
					e.printStackTrace();
				}
		}}

		int sumAverage = IntStream.of(avg).sum();
		int totalAverage = sumAverage / nCourses;
		System.out.println("Total average is: " + totalAverage);
		JOptionPane.showMessageDialog(null, "The total average is: " + totalAverage);
		
		if (guest == false) {
			new UserMain(user_id, name, pswrd, null);
		}
	}
}
